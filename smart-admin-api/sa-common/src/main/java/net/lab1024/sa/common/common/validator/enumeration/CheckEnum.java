package net.lab1024.sa.common.common.validator.enumeration;

import net.lab1024.sa.common.common.enumeration.BaseEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom attribute validation annotation to conveniently validate
 * whether the value of an attribute is a valid enum value.
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class) // Custom validator handler class
public @interface CheckEnum {

    /**
     * Default error message.
     *
     * @return String
     */
    String message();

    /**
     * Enum class object that must implement the BaseEnum interface.
     *
     * @return
     */
    Class<? extends BaseEnum> value();

    /**
     * Whether it is mandatory.
     *
     * @return boolean
     */
    boolean required() default false;

    // The next two attributes must be added, otherwise an error will occur.
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
