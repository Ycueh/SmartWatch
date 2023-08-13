package net.lab1024.sa.common.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户级别的错误码（用户引起的错误返回码，可以不用关注）
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021/09/21 22:12:27
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
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
}
