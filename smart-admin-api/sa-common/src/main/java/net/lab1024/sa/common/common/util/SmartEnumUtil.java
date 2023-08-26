package net.lab1024.sa.common.common.util;

import net.lab1024.sa.common.common.enumeration.BaseEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enumeration utility class
 *
 */
public class SmartEnumUtil {

    /**
     * Check if the parameter is valid compared to the enumeration class
     *
     */
    public static boolean checkEnum(Object value, Class<? extends BaseEnum> enumClass) {
        if (null == value) {
            return false;
        }
        return Stream.of(enumClass.getEnumConstants()).anyMatch(e -> e.equalsValue(value));
    }

    /**
     * Create an array with unique values, each value not included in the other given arrays.
     *
     * @param enumClass
     * @param exclude
     * @param <T>
     * @return
     */
    public static <T extends BaseEnum> List<Object> differenceValueList(Class<? extends BaseEnum> enumClass, T... exclude) {
        HashSet<Object> valueSet = new HashSet<>();
        if (exclude != null) {
            valueSet.addAll(Stream.of(exclude).map(BaseEnum::getValue).collect(Collectors.toSet()));
        }

        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> !valueSet.contains(e.getValue()))
                .map(BaseEnum::getValue)
                .collect(Collectors.toList());
    }

    /**
     * Get the description of the enumeration class in the format value : info
     *
     * @param enumClass
     * @return String
     */
    public static String getEnumDesc(Class<? extends BaseEnum> enumClass) {
        BaseEnum[] enums = enumClass.getEnumConstants();
        // value : info format
        StringBuilder sb = new StringBuilder();
        for (BaseEnum baseEnum : enums) {
            sb.append(baseEnum.getValue()).append(": ").append(baseEnum.getDesc()).append(", ");
        }
        return sb.toString();
    }

    /**
     * Get the description of the enumeration class instance that matches the parameter
     *
     * @param value     Parameter
     * @param enumClass Enumeration class must implement the BaseEnum interface
     * @return String Returns null if no matching enumeration is found
     */
    public static String getEnumDescByValue(Object value, Class<? extends BaseEnum> enumClass) {
        if (null == value) {
            return null;
        }
        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> e.equalsValue(value))
                .findFirst()
                .map(BaseEnum::getDesc)
                .orElse(null);
    }

    public static <T> String getEnumDescByValueList(Collection<T> values, Class<? extends BaseEnum> enumClass) {
        if (CollectionUtils.isEmpty(values)) {
            return "";
        }
        return Stream.of(enumClass.getEnumConstants()).filter(e -> values.contains(e.getValue())).map(BaseEnum::getDesc).collect(Collectors.joining(","));
    }

    /**
     * Get an instance of the enumeration class based on the parameter
     *
     */
    public static <T extends BaseEnum> T getEnumByValue(Object value, Class<T> enumClass) {
        if (null == value) {
            return null;
        }
        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> e.equalsValue(value))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get an instance of the enumeration class based on the instance description
     *
     * @param desc      Parameter description
     * @param enumClass Enumeration class must implement the BaseEnum interface
     * @return BaseEnum Returns null if no matching value is found
     */
    public static <T extends BaseEnum> T getEnumByDesc(String desc, Class<T> enumClass) {
        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> Objects.equals(e.getDesc(), desc))
                .findFirst()
                .orElse(null);
    }

    public static <T extends BaseEnum> T getEnumByName(String name, Class<T> enumClass) {
        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> StringUtils.equalsIgnoreCase(e.toString(), name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Inject based on lambda getter/setter
     *
     * @param list
     * @param getter
     * @param setter
     * @param enumClass
     * @param <T>
     */
    public static <T> void inject(List<T> list, Function<T, Integer> getter, BiConsumer<T, String> setter, Class<? extends BaseEnum> enumClass) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (T t : list) {
            Integer enumValue = getter.apply(t);
            if (enumValue != null) {
                setter.accept(t, getEnumDescByValue(enumValue, enumClass));
            }
        }
    }
}
