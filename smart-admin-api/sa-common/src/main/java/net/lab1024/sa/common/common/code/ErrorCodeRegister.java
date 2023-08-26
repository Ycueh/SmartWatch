package net.lab1024.sa.common.common.code;

import static net.lab1024.sa.common.common.code.ErrorCodeRangeContainer.register;

/**
 * Register code status.
 * ps: Why manually register in this less elegant way here?
 * The main reason is to be unified, clear, and to view all currently defined status codes,
 * making it easier for subsequent maintenance.
 *
 */
public class ErrorCodeRegister {

    static {

        // System error codes
        register(SystemErrorCode.class, 10001, 20000);

        // Unexpected error codes
        register(UnexpectedErrorCode.class, 20001, 30000);

        // Common user error codes
        register(UserErrorCode.class, 30001, 40000);

    }


    public static int initialize() {
        return ErrorCodeRangeContainer.initialize();
    }

    public static void main(String[] args) {
        ErrorCodeRegister.initialize();
    }

}
