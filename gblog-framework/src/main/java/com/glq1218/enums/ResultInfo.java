package com.glq1218.enums;

import lombok.Data;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午1:12
 * @Description: TODO
 */
public enum ResultInfo {
    SUCCESS(200, "操作成功"),
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATION_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "系统错误"),
    USER_EXISTS(501, "用户名已存在"),
    PHONE_EXISTS(502, "手机号已存在"),
    EMAIL_EXISTS(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必须填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误");

    private Integer code;
    private String msg;

    ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
