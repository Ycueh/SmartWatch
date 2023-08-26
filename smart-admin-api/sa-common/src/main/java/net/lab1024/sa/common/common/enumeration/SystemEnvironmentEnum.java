package net.lab1024.sa.common.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * System environment enumeration
 *
 */
@AllArgsConstructor
@Getter
public enum SystemEnvironmentEnum implements BaseEnum {
    /**
     * dev
     */
    DEV(SystemEnvironmentNameConst.DEV, "Development Environment"),

    /**
     * test
     */
    TEST(SystemEnvironmentNameConst.TEST, "Testing Environment"),

    /**
     * pre
     */
    PRE(SystemEnvironmentNameConst.PRE, "Pre-release Environment"),

    /**
     * prod
     */
    PROD(SystemEnvironmentNameConst.PROD, "Production Environment");

    private final String value;

    private final String desc;

    public static final class SystemEnvironmentNameConst {
        public static final String DEV = "dev";
        public static final String TEST = "test";
        public static final String PRE = "pre";
        public static final String PROD = "prod";
    }

}
