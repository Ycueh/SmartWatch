package net.lab1024.sa.common.common.security;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.common.common.constant.RequestHeaderConst;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class SecurityTokenFilter extends OncePerRequestFilter {

    private BiFunction<String, HttpServletRequest, UserDetails> userFunction;

    public SecurityTokenFilter(BiFunction<String, HttpServletRequest, UserDetails> userFunction) {
        this.userFunction = userFunction;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Token validation is required, header token takes precedence over token from request query parameter
        String xHeaderToken = request.getHeader(RequestHeaderConst.TOKEN);
        String xRequestToken = request.getParameter(RequestHeaderConst.TOKEN);
        String xAccessToken = xHeaderToken != null ? xHeaderToken : xRequestToken;
        if (StringUtils.isBlank(xAccessToken)) {
            chain.doFilter(request, response);
            return;
        }
        // Clear Spring Security context
        SecurityContextHolder.clearContext();

        UserDetails loginUserDetail = userFunction.apply(xAccessToken, request);
        if (loginUserDetail != null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetail, null, loginUserDetail.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            SmartRequestUtil.setRequestUser((RequestUser) loginUserDetail);
        }
        // If the user is not authorized in the Spring Security context, authorization will fail and enter AuthenticationEntryPointImpl
        chain.doFilter(request, response);
    }
}
