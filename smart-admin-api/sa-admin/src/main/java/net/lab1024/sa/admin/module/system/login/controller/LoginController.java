package net.lab1024.sa.admin.module.system.login.controller;

import cn.hutool.extra.servlet.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.system.login.domain.LoginUserDetail;
import net.lab1024.sa.admin.module.system.login.domain.LoginForm;
import net.lab1024.sa.admin.module.system.login.domain.LoginWatchDetail;
import net.lab1024.sa.admin.module.system.login.service.LoginService;
import net.lab1024.sa.common.common.annoation.NoNeedLogin;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.constant.RequestHeaderConst;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import net.lab1024.sa.common.module.support.captcha.domain.CaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * User login
 *
 */
@RestController
@Api(tags = {AdminSwaggerTagConst.System.SYSTEM_LOGIN})
public class LoginController {

    @Autowired
    private LoginService loginService;

    @NoNeedLogin
    @PostMapping("/login")
    @ApiOperation("login")
    public ResponseDTO<LoginUserDetail> login(@Valid @RequestBody LoginForm loginForm) {
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT);
        return loginService.login(loginForm);
    }

    @NoNeedLogin
    @PostMapping("/login/Watch")
    @ApiOperation("watchLogin")
    public ResponseDTO<LoginWatchDetail> loginToken(@Valid @RequestBody LoginForm loginForm){
        return loginService.watchLogin(loginForm);
    }

    @GetMapping("/login/refresh")
    @ApiOperation("Refresh user info")
    public ResponseDTO<String> refresh() {
        loginService.removeLoginUserDetailCache(SmartRequestUtil.getRequestUserId());
        return ResponseDTO.ok();
    }

    @GetMapping("/login/getLoginInfo")
    @ApiOperation("Acquire login result")
    public ResponseDTO<LoginUserDetail> getLoginInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ResponseDTO.error(UserErrorCode.LOGIN_STATE_INVALID);
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof LoginUserDetail)) {
            return ResponseDTO.error(UserErrorCode.LOGIN_STATE_INVALID);
        }

        LoginUserDetail loginUserDetail = (LoginUserDetail) authentication.getPrincipal();
        loginUserDetail.setLoginPassword(null);
        return ResponseDTO.ok(loginUserDetail);
    }

    @ApiOperation("Log out")
    @GetMapping("/login/logout")
    public ResponseDTO<String> logout(@RequestHeader(value = RequestHeaderConst.TOKEN, required = false) String token) {
        ResponseDTO<String> abc = loginService.logout(token, SmartRequestUtil.getRequestUser());
        return abc;
    }

    @ApiOperation("Get captcha")
    @GetMapping("/login/getCaptcha")
    @NoNeedLogin
    public ResponseDTO<CaptchaVO> getCaptcha() {
        return loginService.getCaptcha();
    }

}
