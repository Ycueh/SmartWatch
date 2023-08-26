package net.lab1024.sa.common.common.security;

import net.lab1024.sa.common.common.annoation.SaAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.prepost.PreInvocationAttribute;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.security.access.prepost.PrePostInvocationAttributeFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is used to automatically add permissions to all interfaces using @SaAuth.checkPermission('%s').
 * %s is the class name.method name
 * This achieves the same effect as using @PreAuthorize("@SaAuth.checkPermission('%s')"),
 * avoiding adding it to all interfaces and reducing workload.
 *
 */
public class SecurityMethodSource extends PrePostAnnotationSecurityMetadataSource {


    private static String EXPRESSION_FORMAT = "@%s.checkPermission('%s')";

    private final PrePostInvocationAttributeFactory attributeFactory;

    private String beanName;


    public SecurityMethodSource(PrePostInvocationAttributeFactory attributeFactory, String beanName) {
        super(attributeFactory);
        this.attributeFactory = attributeFactory;
        this.beanName = beanName;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
        SaAuth saAuth = method.getAnnotation(SaAuth.class);
        if (saAuth == null) {
            return super.getAttributes(method, targetClass);
        }

        ArrayList<ConfigAttribute> configAttributes = new ArrayList(1);
        String classFullName = targetClass.getName();
        String methodName = method.getName();
        String[] classNameArray = StringUtils.split(classFullName, "\\.");
        String controllerName = classNameArray[classNameArray.length - 1];
        String privilegeName = controllerName + "." + methodName;
        String preAuthorizeAttribute = String.format(EXPRESSION_FORMAT, beanName, privilegeName);
        PreInvocationAttribute pre = this.attributeFactory.createPreInvocationAttribute(null, null, preAuthorizeAttribute);
        if (pre != null) {
            configAttributes.add(pre);
        }
        return configAttributes;
    }

}
