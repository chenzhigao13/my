package com.liandi.system.advice;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.liandi.system.response.ResponseDTO;
import com.liandi.system.response.ResponseEnum;

/**
 * 对 ResponseBody 封装
 * 
 * @author czg
 * @date 2019/7/31 16:14
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NotNull MethodParameter returnType,
        @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType,
        @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
        @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {

        if (Objects.isNull(body)) {
            return new ResponseDTO<>(ResponseEnum.OK);
        }

        return new ResponseDTO<>(ResponseEnum.OK, body);

    }
}
