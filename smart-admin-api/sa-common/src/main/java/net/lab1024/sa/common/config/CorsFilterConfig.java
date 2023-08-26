package net.lab1024.sa.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cross origin config
 *
 */
@Configuration
public class CorsFilterConfig {

    @Value("${access-control-allow-origin}")
    private String accessControlAllowOrigin;
    
    /**
     * Cross origin config
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter () {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Set source url
        config.addAllowedOriginPattern(accessControlAllowOrigin);
        // Set source header
        config.addAllowedHeader("*");
        // Set source method
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}