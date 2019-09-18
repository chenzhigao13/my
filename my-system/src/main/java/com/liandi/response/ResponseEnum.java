package com.liandi.response;

/**
 * @author czg
 * @date 2019/7/16 8:23
 * @description 响应常量
 */
public enum ResponseEnum {

    /**
     * 处理成功
     */
    OK("处理成功", 200),
    /**
     * 处理错误
     */
    ERROR("处理错误", 500),
    /**
     * 请求体缺失
     */
    HTTP_MESSAGE_NOT_READABLE_ERROR("请求体缺失", 422),
    /**
     * 鉴权或授权过程出错
     */
    SHIRO_ERROR("鉴权或授权过程出错", 2999),
    /**
     * 用户没有访问权限
     */
    SHIRO_UNAUTHORIZED_ERROR("用户没有访问权限", 2998),
    /**
     * 参数校验错误
     */
    PARAM_VALID_ERROR(306),
    /**
     * 业务错误码
     */
    BUSINESS_ERROR_CODE(2001);

    private String message;
    private int code;

    ResponseEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    ResponseEnum(int code) {
        this(null, code);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
