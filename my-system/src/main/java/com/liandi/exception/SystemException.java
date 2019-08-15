package com.liandi.exception;

import com.liandi.response.ResponseEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author czg
 * @date 2019/7/15 16:46
 * @description system模块异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemException extends RuntimeException {

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误码
     */
    private Integer code;

    public SystemException(ResponseEnum responseEnum) {
        this(responseEnum.getMessage(), responseEnum.getCode());
    }

    public SystemException(String errorMessage, ResponseEnum responseEnum) {
        this(errorMessage, responseEnum.getCode());
    }

    public SystemException(String errorMessage, Integer code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

}
