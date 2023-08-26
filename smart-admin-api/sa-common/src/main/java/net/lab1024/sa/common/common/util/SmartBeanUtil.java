package net.lab1024.sa.common.common.util;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for handling bean-related operations
 *
 */
public class SmartBeanUtil {

    /**
     * Validator
     */
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Copy properties from source bean to target bean
     *
     * @param source Source object to copy from
     * @param target Target object to copy to
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * Copy an object
     *
     * @param source Source object to copy from
     * @param targetClass Class of the target object
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        try {
            T newInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copy a list of objects
     *
     * @param source List of source objects to copy from
     * @param targetClass Class of the target object
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<K> copyList(List<T> source, Class<K> targetClass) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream().map(e -> copy(e, targetClass)).collect(Collectors.toList());
    }

    /**
     * Manually validate the properties of a model object
     * Requires the use of Hibernate Validator validation annotations
     *
     * @param object
     * @return String Returns null if validation passes, otherwise returns the error messages
     */
    public static <T> String verify(T object) {
        // Get validation results
        Set<ConstraintViolation<T>> validate = VALIDATOR.validate(object);
        if (validate.isEmpty()) {
            // Validation passed
            return null;
        }
        // Return error messages
        List<String> messageList = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return messageList.toString();
    }
}
