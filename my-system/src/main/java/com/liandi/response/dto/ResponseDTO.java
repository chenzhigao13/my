package com.liandi.response.dto;

import com.liandi.response.ResponseEnum;

import lombok.Data;

/**
 * @author czg
 * @date 2019/7/16 8:20
 * @description 响应DTO
 */
@Data
public class ResponseDTO<T> {

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private T data;

    public ResponseDTO(String message, Integer code) {
        this(message, code, null);
    }

    private ResponseDTO(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseDTO(ResponseEnum responseEnum) {
        this(responseEnum.getMessage(), responseEnum.getCode(), null);
    }

    public ResponseDTO(ResponseEnum responseEnum, T data) {
        this(responseEnum.getMessage(), responseEnum.getCode(), data);
    }

}
