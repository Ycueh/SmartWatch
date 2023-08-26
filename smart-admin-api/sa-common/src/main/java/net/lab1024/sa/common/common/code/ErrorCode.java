package net.lab1024.sa.common.common.code;

/**
 * Error codes<br>
 * There are three types: 1) System errors, 2) User-level errors, 3) Unexpected errors
 *
 */
public interface ErrorCode {

    /**
     * System level
     */
    String LEVEL_SYSTEM = "system";

    /**
     * User level
     */
    String LEVEL_USER = "user";

    /**
     * Unexpected level
     */
    String LEVEL_UNEXPECTED = "unexpected";

    /**
     * Error code
     *
     * @return
     */
    int getCode();

    /**
     * Error message
     *
     * @return
     */
    String getMsg();

    /**
     * Error level
     *
     * @return
     */
    String getLevel();
}
