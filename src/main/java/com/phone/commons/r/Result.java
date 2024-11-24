package com.phone.commons.r;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 全局统一返回结果类
 */

@Data
public class Result<T> implements Serializable {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 私有化构造方法，禁止在其它类创建对象
     */
    private Result() {
    }

     /**
      * @description 成功执行 ,不返回data
      */
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * @description 成功执行 ,返回data
     * @return com.dazhiruou.blog.commons.R.Result<T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * @description 执行失败
     * @return com.dazhiruou.blog.commons.R.Result<T>
     */
    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * @description 执行失败
     * @return com.dazhiruou.blog.commons.R.Result<T>
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


    /**
     * 设置状态码
     *
     * @param code
     * @return
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回消息
     *
     * @param message
     * @return
     */
    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

}
