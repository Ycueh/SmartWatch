package net.lab1024.sa.admin.module.system.login.service;

import cn.hutool.extra.servlet.ServletUtil;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.system.employee.domain.entity.EmployeeEntity;
import net.lab1024.sa.admin.module.system.employee.service.EmployeePermissionService;
import net.lab1024.sa.admin.module.system.employee.service.EmployeeService;
import net.lab1024.sa.admin.module.system.login.domain.LoginEmployeeDetail;
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
    private EmployeeService employeeService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private EmployeePermissionService employeePermissionService;

    /**
     * 登录信息二级缓存
     */
    private ConcurrentMap<Long, LoginEmployeeDetail> loginUserDetailCache = new ConcurrentLinkedHashMap.Builder<Long, LoginEmployeeDetail>().maximumWeightedCapacity(1000).build();

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
    public ResponseDTO<LoginEmployeeDetail> login(LoginForm loginForm, String ip, String userAgent) {
        // 校验 图形验证码
//        ResponseDTO<String> checkCaptcha = captchaService.checkCaptcha(loginForm);
//        if (!checkCaptcha.getOk()) {
//            return ResponseDTO.error(checkCaptcha);
//        }

        /**
         * 验证账号和账号状态
         */
        EmployeeEntity employeeEntity = employeeService.getByLoginName(loginForm.getLoginName());
        if (null == employeeEntity) {
            return ResponseDTO.userErrorParam("Username does not exist");
        }

        if (employeeEntity.getDisabledFlag()) {
            return ResponseDTO.userErrorParam("You account is banned");
        }
        String requestPassword = EmployeeService.getEncryptPwd(loginForm.getPassword());
        // 生成 登录token，保存token
        String token = tokenService.generateToken(employeeEntity.getEmployeeId(), employeeEntity.getActualName(), UserTypeEnum.ADMIN_EMPLOYEE);

        //获取员工登录信息
        LoginEmployeeDetail loginEmployeeDetail = loadLoginInfo(employeeEntity);
        loginEmployeeDetail.setToken(token);

        // 放入缓存
        loginUserDetailCache.put(employeeEntity.getEmployeeId(), loginEmployeeDetail);

        return ResponseDTO.ok(loginEmployeeDetail);
    }


    /**
     * 获取登录的用户信息
     *
     * @return
     */
    private LoginEmployeeDetail loadLoginInfo(EmployeeEntity employeeEntity) {
        LoginEmployeeDetail loginEmployeeDetail = SmartBeanUtil.copy(employeeEntity, LoginEmployeeDetail.class);
        loginEmployeeDetail.setUserType(UserTypeEnum.ADMIN_EMPLOYEE);


        /**
         * 获取前端菜单和后端权限
         * 1、从数据库获取所有的权限
         * 2、拼凑成菜单和后端权限
         */
        List<MenuVO> menuAndPointsList = employeePermissionService.getEmployeeMenuAndPointsList(employeeEntity.getEmployeeId(), employeeEntity.getAdministratorFlag());
        //前端菜单
        loginEmployeeDetail.setMenuList(menuAndPointsList);
        //后端权限
        loginEmployeeDetail.setAuthorities(employeePermissionService.buildAuthorities(menuAndPointsList));

        return loginEmployeeDetail;
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
    public LoginEmployeeDetail getLoginUserDetail(String token, HttpServletRequest request) {
        Long requestUserId = tokenService.getUserIdAndValidateToken(token);
        if (requestUserId == null) {
            return null;
        }
        // 查询用户信息
        LoginEmployeeDetail loginEmployeeDetail = loginUserDetailCache.get(requestUserId);
        if (loginEmployeeDetail == null) {
            // 员工基本信息
            EmployeeEntity employeeEntity = employeeService.getById(requestUserId);
            if (employeeEntity == null) {
                return null;
            }

            loginEmployeeDetail = this.loadLoginInfo(employeeEntity);
            loginEmployeeDetail.setToken(token);
            loginUserDetailCache.put(requestUserId, loginEmployeeDetail);
        }

        //更新请求ip和user agent
        loginEmployeeDetail.setUserAgent(ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT));
        loginEmployeeDetail.setIp(ServletUtil.getClientIP(request));

        return loginEmployeeDetail;
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
