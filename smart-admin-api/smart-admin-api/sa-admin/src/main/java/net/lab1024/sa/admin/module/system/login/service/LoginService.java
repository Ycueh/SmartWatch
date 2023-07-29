package net.lab1024.sa.admin.module.system.login.service;

import cn.hutool.extra.servlet.ServletUtil;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.system.user.domain.entity.UserEntity;
import net.lab1024.sa.admin.module.system.user.service.UserPermissionService;
import net.lab1024.sa.admin.module.system.user.service.UserService;
import net.lab1024.sa.admin.module.system.login.domain.LoginUserDetail;
import net.lab1024.sa.admin.module.system.login.domain.LoginForm;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.common.common.constant.RequestHeaderConst;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.module.support.captcha.CaptchaService;
import net.lab1024.sa.common.module.support.captcha.domain.CaptchaVO;
import net.lab1024.sa.common.module.support.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * 员工 登录服务
 *
 * @Author 1024创新实验室: 开云
 * @Date 2021-12-01 22:56:34
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@Slf4j
@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserPermissionService userPermissionService;

    /**
     * 登录信息二级缓存
     */
    private ConcurrentMap<Long, LoginUserDetail> loginUserDetailCache = new ConcurrentLinkedHashMap.Builder<Long, LoginUserDetail>().maximumWeightedCapacity(1000).build();

    /**
     * 获取验证码
     *
     * @return
     */
    public ResponseDTO<CaptchaVO> getCaptcha() {
        return ResponseDTO.ok(captchaService.generateCaptcha());
    }

    /**
     * 员工登陆
     *
     * @param loginForm
     * @return 返回用户登录信息
     */
    public ResponseDTO<LoginUserDetail> login(LoginForm loginForm, String ip, String userAgent) {
        // 校验 图形验证码
//        ResponseDTO<String> checkCaptcha = captchaService.checkCaptcha(loginForm);
//        if (!checkCaptcha.getOk()) {
//            return ResponseDTO.error(checkCaptcha);
//        }

        /**
         * 验证账号和账号状态
         */
        UserEntity userEntity = userService.getByLoginName(loginForm.getLoginName());
        if (null == userEntity) {
            return ResponseDTO.userErrorParam("Username does not exist");
        }

        if (userEntity.getDisabledFlag()) {
            return ResponseDTO.userErrorParam("You account is banned");
        }
        String requestPassword = UserService.getEncryptPwd(loginForm.getPassword());
        // 生成 登录token，保存token
        String token = tokenService.generateToken(userEntity.getUserId(), userEntity.getActualName(), UserTypeEnum.ADMIN_USER);

        //获取员工登录信息
        LoginUserDetail loginUserDetail = loadLoginInfo(userEntity);
        loginUserDetail.setToken(token);

        // 放入缓存
        loginUserDetailCache.put(userEntity.getUserId(), loginUserDetail);

        return ResponseDTO.ok(loginUserDetail);
    }


    /**
     * 获取登录的用户信息
     *
     * @return
     */
    private LoginUserDetail loadLoginInfo(UserEntity userEntity) {
        LoginUserDetail loginUserDetail = SmartBeanUtil.copy( userEntity, LoginUserDetail.class);
        loginUserDetail.setUserType(UserTypeEnum.ADMIN_USER);


        /**
         * 获取前端菜单和后端权限
         * 1、从数据库获取所有的权限
         * 2、拼凑成菜单和后端权限
         */
        List<MenuVO> menuAndPointsList = userPermissionService.getUserMenuAndPointsList(userEntity.getUserId(), userEntity.getAdministratorFlag());
        //前端菜单
        loginUserDetail.setMenuList(menuAndPointsList);
        //后端权限
        loginUserDetail.setAuthorities(userPermissionService.buildAuthorities(menuAndPointsList));

        return loginUserDetail;
    }


    /**
     * 移除用户信息缓存
     *
     * @param requestUserId
     */
    public void removeLoginUserDetailCache(Long requestUserId) {
        loginUserDetailCache.remove(requestUserId);
    }

    /**
     * 根据登陆token 获取员请求工信息
     *
     * @param
     * @return
     */
    public LoginUserDetail getLoginUserDetail(String token, HttpServletRequest request) {
        Long requestUserId = tokenService.getUserIdAndValidateToken(token);
        if (requestUserId == null) {
            return null;
        }
        // 查询用户信息
        LoginUserDetail loginUserDetail = loginUserDetailCache.get(requestUserId);
        if (loginUserDetail == null) {
            // 员工基本信息
            UserEntity userEntity = userService.getById(requestUserId);
            if (userEntity == null) {
                return null;
            }

            loginUserDetail = this.loadLoginInfo(userEntity);
            loginUserDetail.setToken(token);
            loginUserDetailCache.put(requestUserId, loginUserDetail);
        }

        //更新请求ip和user agent
        loginUserDetail.setUserAgent(ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT));
        loginUserDetail.setIp(ServletUtil.getClientIP(request));

        return loginUserDetail;
    }


    /**
     * 退出登陆，清除token缓存
     *
     * @return
     */
    public ResponseDTO<String> logout(String token, RequestUser requestUser) {
        loginUserDetailCache.remove(requestUser.getUserId());
        tokenService.removeToken(token);
        return ResponseDTO.ok();
    }
}
