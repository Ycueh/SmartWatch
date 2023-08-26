package net.lab1024.sa.common.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal 工具类
 *
 */
public class SmartBigDecimalUtil {

    /**
     * Decimal Places for Amount: 2
     */
    public static final int AMOUNT_DECIMAL_PLACES = 2;

    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    /**
     * Amount Calculation Methods: Rounded with 2 Decimal Places
     */
    public static class Amount {

        public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
            return setScale(num1.add(num2), AMOUNT_DECIMAL_PLACES);
        }

        public static BigDecimal multiply(BigDecimal num1, BigDecimal num2) {
            return setScale(num1.multiply(num2), AMOUNT_DECIMAL_PLACES);
        }

        public static BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
            return setScale(num1.subtract(num2), AMOUNT_DECIMAL_PLACES);
        }

        public static BigDecimal divide(BigDecimal num1, BigDecimal num2) {
            return setScale(num1.divide(num2, RoundingMode.HALF_UP), AMOUNT_DECIMAL_PLACES);
        }
    }



    public static BigDecimal add(BigDecimal num1, BigDecimal num2, int point) {
        return setScale(num1.add(num2), point);
    }


    public static BigDecimal add(int point, BigDecimal... array) {
        BigDecimal res = new BigDecimal(0);
        for (BigDecimal item : array) {
            if (item == null) {
                res = res.add(BigDecimal.ZERO);
            } else {
                res = res.add(item);
            }
        }
        return setScale(res, point);
    }


    public static BigDecimal multiply(BigDecimal num1, BigDecimal num2, int point) {
        return setScale(num1.multiply(num2), point);
    }


    public static BigDecimal subtract(BigDecimal num1, BigDecimal num2, int point) {
        return setScale(num1.subtract(num2), point);
    }


    public static BigDecimal divide(BigDecimal num1, BigDecimal num2, int point) {
        return num1.divide(num2, point, RoundingMode.HALF_UP);
    }


    public static BigDecimal setScale(BigDecimal num, int point) {
        return num.setScale(point, RoundingMode.HALF_UP);
    }



    public static boolean isGreaterThan(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) > 0;
    }


    public static boolean isGreaterOrEqual(BigDecimal num1, BigDecimal num2) {
        return isGreaterThan(num1, num2) || equals(num1, num2);
    }


    public static boolean isLessThan(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) < 0;
    }


    public static boolean isLessOrEqual(BigDecimal num1, BigDecimal num2) {
        return isLessThan(num1, num2) || equals(num1, num2);
    }


    public static boolean equals(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == 0;
    }


    public static BigDecimal percent(Integer num1, Integer num2, int point) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        if (num2.equals(0)) {
            return BigDecimal.ZERO;
        }
        return percent(new BigDecimal(num1), new BigDecimal(num2), point);
    }


    public static BigDecimal percent(BigDecimal num1, BigDecimal num2, int point) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        if (equals(BigDecimal.ZERO, num2)) {
            return BigDecimal.ZERO;
        }
        BigDecimal percent = num1.divide(num2, point + 2, RoundingMode.HALF_UP);
        return percent.multiply(ONE_HUNDRED).setScale(point);
    }


    public static BigDecimal max(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) > 0 ? num1 : num2;
    }

    public static BigDecimal min(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) < 0 ? num1 : num2;
    }

    public static void main(String[] args) {
        System.out.println(percent(new BigDecimal("3"), new BigDecimal("11"), 2));
        System.out.println(percent(new BigDecimal("8"), new BigDecimal("11"), 2));
    }
}
