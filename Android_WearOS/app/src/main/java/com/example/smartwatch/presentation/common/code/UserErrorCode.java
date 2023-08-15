package com.example.smartwatch.presentation.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    PARAM_ERROR(30001, "Parameter Error"),

    DATA_NOT_EXIST(30002, "Data does not exist"),

    ALREADY_EXIST(30003, "Data exists"),

    REPEAT_SUBMIT(30004, "Do not submit form repeatedly! "),

    NO_PERMISSION(30005, "No permission"),

    DEVELOPING(30006, "Developing"),

    LOGIN_STATE_INVALID(30007, "You are not logged in or your login has expired. Please log in again!"),

    USER_STATUS_ERROR(30008, "User status error"),

    FORM_REPEAT_SUBMIT(30009, "Do not submit form repeatedly!");

    private final int code;

    private final String msg;

    private final String level;

    UserErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_USER;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public String getLevel() {
        return null;
    }
}
