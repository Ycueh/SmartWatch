package net.lab1024.sa.common.common.code;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Error code registration container
 */
class ErrorCodeRangeContainer {

    /**
     * All error codes are greater than 10000
     */
    static final int MIN_START_CODE = 10000;

    static final Map<Class<? extends ErrorCode>, ImmutablePair<Integer, Integer>> CODE_RANGE_MAP = new ConcurrentHashMap<>();

    /**
     * Used for counting
     */
    static int errorCounter = 0;

    /**
     * Register status code.
     * Validate for duplicates and out-of-bound values.
     *
     * @param clazz
     * @param start
     * @param end
     */
    static void register(Class<? extends ErrorCode> clazz, int start, int end) {
        String simpleName = clazz.getSimpleName();

// Check if the class is an Enum
        if (!clazz.isEnum()) {
            throw new ExceptionInInitializerError(String.format("<<ErrorCodeRangeValidator>> error: %s is not an Enum class!", simpleName));
        }

// Check if the starting code is greater than the ending code
        if (start > end) {
            throw new ExceptionInInitializerError(String.format("<<ErrorCodeRangeValidator>> error: %s start must be less than the end!", simpleName));
        }

// Check if the starting code is less than or equal to the minimum allowed code
        if (start <= MIN_START_CODE) {
            throw new ExceptionInInitializerError(String.format("<<ErrorCodeRangeValidator>> error: %s start must be greater than %s!", simpleName, MIN_START_CODE));
        }

// Check if the error code range is already registered
        boolean containsKey = CODE_RANGE_MAP.containsKey(clazz);
        if (containsKey) {
            throw new ExceptionInInitializerError(String.format("<<ErrorCodeRangeValidator>> error: Enum %s already exists!", simpleName));
        }

// Validate if the provided code range overlaps with any existing code ranges
        CODE_RANGE_MAP.forEach((k, v) -> {
            if (isExistOtherRange(start, end, v)) {
                throw new IllegalArgumentException(String.format("<<ErrorCodeRangeValidator>> error: %s[%d,%d] overlaps with class:%s[%d,%d]", simpleName, start, end,
                        k.getSimpleName(), v.getLeft(), v.getRight()));
            }
        });

// Validate and collect all the error codes from the Enum
        List<Integer> codeList = Stream.of(clazz.getEnumConstants()).map(codeEnum -> {
            Integer code = codeEnum.getCode();
            if (code < start || code > end) {
                throw new IllegalArgumentException(String.format("<<ErrorCodeRangeValidator>> error: %s[%d,%d] code %d is out of range", simpleName, start, end, code));
            }
            return code;
        }).collect(Collectors.toList());

// Check for duplicate error codes
        List<Integer> distinctCodeList = codeList.stream().distinct().collect(Collectors.toList());
        Collection<Integer> subtract = CollectionUtils.subtract(codeList, distinctCodeList);
        if (CollectionUtils.isNotEmpty(subtract)) {
            throw new IllegalArgumentException(String.format("<<ErrorCodeRangeValidator>> error: %s code %s is duplicated!", simpleName, subtract));
        }

// Register the error code range for the Enum
        CODE_RANGE_MAP.put(clazz, ImmutablePair.of(start, end));

// Update the error code counter
        errorCounter = errorCounter + distinctCodeList.size();
    }

    /**
     * Check if it exists within another range.
     *
     * @param start
     * @param end
     * @param range
     * @return
     */
    private static boolean isExistOtherRange(int start, int end, ImmutablePair<Integer, Integer> range) {
        if (start >= range.getLeft() && start <= range.getRight()) {
            return true;
        }

        if (end >= range.getLeft() && end <= range.getRight()) {
            return true;
        }

        return false;
    }

    /**
     * Initialization
     */
    static int initialize() {
        return errorCounter;
    }

}
