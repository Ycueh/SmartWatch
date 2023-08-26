package net.lab1024.sa.common.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Unexpected error codes (i.e., when something impossible happens, return codes of this type should be highly regarded)
 *
 */
@Getter
@AllArgsConstructor
public enum UnexpectedErrorCode implements ErrorCode {

    BUSINESS_HANDING(20001, "Uh~ Business is busy, please try again later"),
    PAY_ORDER_ID_ERROR(20002, "An exception occurred with the payment order id, please contact technical staff for investigation"),

    ;

    private final int code;

    private final String msg;

    private final String level;

    UnexpectedErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_UNEXPECTED;
    }

}
