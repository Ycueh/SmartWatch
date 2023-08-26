package net.lab1024.sa.admin.config;

import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import net.lab1024.sa.common.module.support.jwe.JweAspect;
import net.lab1024.sa.common.module.support.jwe.JweUserKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Operation log aspect configuration
 *
 */
@Configuration
public class JweAspectConfig {

    /**
     * Configuration
     */
    @Bean
    public JweAspect jweConfig() {
        return new JweAspect((request -> {
            RequestUser requestUser = SmartRequestUtil.getRequestUser();
            JweUserKey userKey = new JweUserKey();
            userKey.setUserId(requestUser.getUserId());
            userKey.setUserName(requestUser.getUserName());
            userKey.setExtData(requestUser.getUserType().getValue().toString());
            return userKey;
        }));
    }
}