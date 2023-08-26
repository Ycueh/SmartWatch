package net.lab1024.sa.admin.module.system.login.service;

import cn.hutool.extra.servlet.ServletUtil;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.system.login.domain.LoginWatchDetail;
import net.lab1024.sa.admin.module.system.multiuser.service.MultiUserService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * User Login service
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

    @Autowired
    private MultiUserService multiUserService;

    /**
     * Login info cache
     */
    private ConcurrentMap<Long, LoginUserDetail> loginUserDetailCache = new ConcurrentLinkedHashMap.Builder<Long, LoginUserDetail>().maximumWeightedCapacity(1000).build();

    /**
     * Get captcha
     *
     * @return
     */
    public ResponseDTO<CaptchaVO> getCaptcha() {
        return ResponseDTO.ok(captchaService.generateCaptcha());
    }

    /**
     * User login
     *
     * @param loginForm
     * @return Login user detail
     */
    public ResponseDTO<LoginUserDetail> login(LoginForm loginForm) {
        // Check Captcha
//        ResponseDTO<String> checkCaptcha = captchaService.checkCaptcha(loginForm);
//        if (!checkCaptcha.getOk()) {
//            return ResponseDTO.error(checkCaptcha);
//        }

        /**
         * Check account
         */
        UserEntity userEntity = userService.getByLoginName(loginForm.getLoginName());
        if (null == userEntity) {
            return ResponseDTO.userErrorParam("Account does not exist");
        }

        if (userEntity.getDisabledFlag()) {
            return ResponseDTO.userErrorParam("You account is banned");
        }
        String requestPassword = UserService.getEncryptPwd(loginForm.getPassword());
        if (!userEntity.getLoginPwd().equals(requestPassword)) {
            return ResponseDTO.userErrorParam("Account or password error！");
        }
        // Generate token
        String token = tokenService.generateToken(userEntity.getUserId(), userEntity.getActualName(), UserTypeEnum.ADMIN_USER);

        //Acquire user detail
        LoginUserDetail loginUserDetail = loadLoginInfo(userEntity);
        loginUserDetail.setToken(token);

        // Save into cache
        loginUserDetailCache.put(userEntity.getUserId(), loginUserDetail);
        multiUserService.choose(userEntity.getUserId());
        return ResponseDTO.ok(loginUserDetail);
    }

    public ResponseDTO<LoginWatchDetail> watchLogin(LoginForm loginForm) {
        /**
         * Check account
         */
        UserEntity userEntity = userService.getByLoginName(loginForm.getLoginName());
        if (null == userEntity) {
            return ResponseDTO.userErrorParam("Account does not exist");
        }

        if (userEntity.getDisabledFlag()) {
            return ResponseDTO.userErrorParam("You account is banned");
        }
        String requestPassword = UserService.getEncryptPwd(loginForm.getPassword());
        if (!userEntity.getLoginPwd().equals(requestPassword)) {
            return ResponseDTO.userErrorParam("Account or password error！");
        }
        // Generate token
        String token = tokenService.generateToken(userEntity.getUserId(), userEntity.getActualName(), UserTypeEnum.ADMIN_USER);

        //Acquire user detail
        LoginUserDetail loginUserDetail = loadLoginInfo(userEntity);
        loginUserDetail.setToken(token);
        LoginWatchDetail loginWatchDetail = SmartBeanUtil.copy(userEntity,LoginWatchDetail.class);
        loginWatchDetail.setToken(token);

        // Save into cache
        loginUserDetailCache.put(userEntity.getUserId(), loginUserDetail);
        return ResponseDTO.ok(loginWatchDetail);
    }



    /**
     * Acquire user info
     *
     * @return
     */
    private LoginUserDetail loadLoginInfo(UserEntity userEntity) {
        LoginUserDetail loginUserDetail = SmartBeanUtil.copy( userEntity, LoginUserDetail.class);
        loginUserDetail.setUserType(UserTypeEnum.ADMIN_USER);


        /**
         * Acquire front-end menu and back-end authorities
         */
        List<MenuVO> menuAndPointsList = userPermissionService.getUserMenuAndPointsList(userEntity.getUserId(), userEntity.getAdministratorFlag());
        //Front-end menu
        loginUserDetail.setMenuList(menuAndPointsList);
        //Back-end authorities
        loginUserDetail.setAuthorities(userPermissionService.buildAuthorities(menuAndPointsList));

        return loginUserDetail;
    }


    /**
     * Remove cache
     *
     * @param requestUserId
     */
    public void removeLoginUserDetailCache(Long requestUserId) {
        loginUserDetailCache.remove(requestUserId);
    }

    /**
     * Acquire user detail based on token
     *
     * @param
     * @return
     */
    public LoginUserDetail getLoginUserDetail(String token, HttpServletRequest request) {
        Long requestUserId = tokenService.getUserIdAndValidateToken(token);
        if (requestUserId == null) {
            return null;
        }
        // Search user info
        LoginUserDetail loginUserDetail = loginUserDetailCache.get(requestUserId);
        if (loginUserDetail == null) {
            // user info
            UserEntity userEntity = userService.getById(requestUserId);
            if (userEntity == null) {
                return null;
            }

            loginUserDetail = this.loadLoginInfo(userEntity);
            loginUserDetail.setToken(token);
            loginUserDetailCache.put(requestUserId, loginUserDetail);
        }

        //update ip and user agent
        loginUserDetail.setUserAgent(ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT));
        loginUserDetail.setIp(ServletUtil.getClientIP(request));

        return loginUserDetail;
    }


    /**
     * logout
     *
     * @return
     */
    public ResponseDTO<String> logout(String token, RequestUser requestUser) {
        loginUserDetailCache.remove(requestUser.getUserId());
        //TODO logout update
        multiUserService.updateFile(requestUser.getUserId());
        //multiUserService.deleteCurrentFile();
        tokenService.removeToken(token);
        return ResponseDTO.ok();
    }
}
