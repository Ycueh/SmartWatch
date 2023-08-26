package net.lab1024.sa.common.common.validator.enumeration;

import net.lab1024.sa.common.common.enumeration.BaseEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum validator
 */
public class EnumValidator implements ConstraintValidator<CheckEnum, Object> {

    /**
     * List of Enum objects
     */
    private List<Object> enumValList;

    private boolean required;

    @Override
    public void initialize(CheckEnum constraintAnnotation) {
        // Retrieve the enum class object from the annotation
        required = constraintAnnotation.required();
        Class<? extends BaseEnum> enumClass = constraintAnnotation.value();
        enumValList = Stream.of(enumClass.getEnumConstants()).map(BaseEnum::getValue).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // Check if the field is mandatory
        if (null == value) {
            return !required;
        }

        if (value instanceof List) {
            // If the data is of type List
            return this.checkList((List<Object>) value);
        }

        // Validate if it's a valid enum value
        return enumValList.contains(value);
    }

    /**
     * Validate the collection type
     *
     * @param list
     * @return
     */
    private boolean checkList(List<Object> list) {
        if (required && list.isEmpty()) {
            // If mandatory, the list cannot be empty
            return false;
        }
        // Check for duplicates
        long count = list.stream().distinct().count();
        if (count != list.size()) {
            return false;
        }
        return enumValList.containsAll(list);
    }
}
