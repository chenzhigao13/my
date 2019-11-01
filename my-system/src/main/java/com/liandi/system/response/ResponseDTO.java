package com.liandi.system.response;

import lombok.Data;

/**
 * 响应DTO
 * 
 * @author czg
 * @date 2019/7/16 8:20
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

    public ResponseDTO(String message, ResponseEnum responseEnum) {
        this(message, responseEnum.getCode(), null);
    }

    public ResponseDTO(String message, Integer code) {
        this(message, code, null);
    }

    public ResponseDTO(ResponseEnum responseEnum) {
        this(responseEnum.getMessage(), responseEnum.getCode(), null);
    }

    public ResponseDTO(ResponseEnum responseEnum, T data) {
        this(responseEnum.getMessage(), responseEnum.getCode(), data);
    }

    private ResponseDTO(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

}
