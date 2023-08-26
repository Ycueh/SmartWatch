package net.lab1024.sa.admin.config;

import net.lab1024.sa.admin.module.system.login.service.LoginService;
import net.lab1024.sa.common.common.security.AbstractSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiFunction;

/**
 * Security Config
 *
 */
@Configuration
public class SecurityConfig extends AbstractSecurityConfig {
    /**
     * Token Parsing Utility Class
     */
    @Autowired
    private LoginService loginService;

    @Override
    protected BiFunction<String, HttpServletRequest, UserDetails> userFunction() {
        return (token, request) -> loginService.getLoginUserDetail(token, request);
    }

    @Override
    protected String[] getAuthenticatedUrlPatterns() {
        return new String[]{"/**"};
    }


}
