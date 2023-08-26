package net.lab1024.sa.common.common.util;

import java.util.regex.Pattern;

/**
 * Verification util
 *
 */
public class SmartVerificationUtil {

    /**
     * Phone number
     */
    public static final String PHONE_REGEXP = "^07[0-9]{9}$";

    /**
     * Password
     */
    public static final String PWD_REGEXP = "^[A-Za-z0-9.]{6,15}$";

    /**
     * Integer
     */
    public static final String INTEGER = "^-?[1-9]\\d*$";


    public static void main(String[] args) {
        boolean matches = Pattern.matches(INTEGER, "1");
        System.out.println(matches);
    }
}
