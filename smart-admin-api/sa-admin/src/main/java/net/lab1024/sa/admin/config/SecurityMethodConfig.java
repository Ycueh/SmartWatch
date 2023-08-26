package net.lab1024.sa.admin.config;

import net.lab1024.sa.admin.module.system.login.domain.LoginUserDetail;
import net.lab1024.sa.common.common.annoation.SaAuth;
import net.lab1024.sa.common.common.security.SecurityMethodSource;
import net.lab1024.sa.common.common.security.SecurityPermissionCheckService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.ExpressionBasedAnnotationAttributeFactory;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;

/**
 * 1. Use the class name plus method name as the permission string verification mode. <br>
 * 2. Overriding MethodSecurityMetadataSource will optimize the security configuration. Just add the @saAuth annotation to the method, and the method will have permissions (with the permission string being the class name plus method name). This removes the need for manual setup, reducing backend development costs. <br>
 *    Security will no longer use the permission string for access control. <br>
 * 3. Security will control access based on the interface permissions under the corresponding permission string. <br>
 * 4. Using this configuration, the original @PreAuthorize is still effective. <br>
 * 5. If this configuration is not needed, the @EnableGlobalMethodSecurity annotation needs to be added to the SecurityConfig class.
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityMethodConfig extends GlobalMethodSecurityConfiguration {

    @Bean(SaAuth.saAuth)
    public SecurityPermissionCheckService securityPermissionCheckService() {
        return new SecurityPermissionCheckService() {
            @Override
            public boolean checkPermission(Authentication authentication, String permission) {
                LoginUserDetail loginUserDetail = (LoginUserDetail) authentication.getPrincipal();
                if (loginUserDetail.getAdministratorFlag()) {
                    return true;
                }
                return super.permissionJudge(loginUserDetail, permission);
            }
        };
    }

    @Override
    public MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        ExpressionBasedAnnotationAttributeFactory attributeFactory = new ExpressionBasedAnnotationAttributeFactory(this.getExpressionHandler());
        return new SecurityMethodSource(attributeFactory, SaAuth.saAuth);
    }
}
