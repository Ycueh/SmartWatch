package net.lab1024.sa.common.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Spring Security Configuration
 *
 */
public abstract class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private List<String> noNeedLoginUrlList;

    @Autowired
    private List<String> ignoreUrlList;

    /**
     * Function to retrieve user information from token
     *
     * @return
     */
    protected abstract BiFunction<String, HttpServletRequest, UserDetails> userFunction();

    /**
     * URLs requiring authentication
     *
     * @return
     */
    protected abstract String[] getAuthenticatedUrlPatterns();

    /**
     * URLs that do not require login
     *
     * @return
     */
    protected String[] getNoNeedLoginUrl() {
        return noNeedLoginUrlList.toArray(new String[noNeedLoginUrlList.size()]);
    }

    /**
     * Ignored URLs
     *
     * @return
     */
    protected String[] getIgnoreUrlList() {
        return ignoreUrlList.toArray(new String[ignoreUrlList.size()]);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable CSRF as session is not used
                .csrf().disable()
                // Authentication failure handling
                .exceptionHandling().authenticationEntryPoint(new SecurityAuthenticationFailHandler()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Request filtering
                .authorizeRequests()
                // Ignored URLs
                .antMatchers(this.getIgnoreUrlList()).permitAll()
                // URLs not requiring login
                .antMatchers(this.getNoNeedLoginUrl()).permitAll()
                // URLs requiring permission validation
                .antMatchers(getAuthenticatedUrlPatterns()).authenticated();

        // Token filter for validation
        httpSecurity.addFilterBefore(new SecurityTokenFilter(this.userFunction()), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(corsFilter, SecurityTokenFilter.class);
        // Disable Spring Security's X-Frame-Options to prevent web pages from being framed
        httpSecurity.headers().frameOptions().disable();
    }
}
