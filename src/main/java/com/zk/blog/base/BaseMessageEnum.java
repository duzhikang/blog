package com.zk.blog.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应基本信息
 */
public enum  BaseMessageEnum {
    SUCCESS(0, "成功"),
    ERROR(-1, "系统错误"),
    LOGOUT_SUCCESS(1001, "退出成功"),
    LOGIN_SUCCESS(1002, "登陆成功"),
    REGISTER_SUCCESS(1003, "注册成功")
    ;

    private Integer code;

    private String meesage;

    BaseMessageEnum(Integer code, String meesage) {
        this.code = code;
        this.meesage = meesage;
    }

    public Integer getCode() {
        return code;
    }

    public String getMeesage() {
        return meesage;
    }
}
