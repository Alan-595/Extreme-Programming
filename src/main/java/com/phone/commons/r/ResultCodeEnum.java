package com.phone.commons.r;

import lombok.Getter;


/**
 * @Description 返回结果枚举
 */

@Getter
public enum ResultCodeEnum {
    /**
     * 业务相关枚举类
     */
    SUCCESS(1000, "操作成功"),
    SEARCH_ERROR(1001, "查询失败"),
    INSERT_ERROR(1002, "新增失败"),
    UPDATE_ERROR(1003, "修改失败"),
    DELETE_ERROR(1004, "删除失败"),
    NO_LOGIN(1012, "匿名用户，请您先登陆"),
    NO_AUTH(1013, "没有权限，请联系管理员"),
    USERNAME_PASSWORD_ERROR(1014, "用户名或密码错误"),
    OPERATION_ERROR(1020, "操作失败");


    /**
     * 提示码
     */
    private final Integer code;
    /**
     * 提示信息
     */
    private final String message;

    /**
     * 返回结果枚举
     *
     * @param code    状态码
     * @param message 返回结果信息
     */
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
