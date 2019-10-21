package com.liandi.system.advice;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.liandi.system.response.ResponseEnum;
import com.liandi.system.response.dto.ResponseDTO;

/**
 * @author czg
 * @date 2019/7/31 16:14
 * @description 对 ResponseBody 封装
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final String NONE_RETURN = "void";

    @Override
    public boolean supports(MethodParameter returnType,
        @NotNull Class<? extends HttpMessageConverter<?>> converterType) {

        if (NONE_RETURN.equals(returnType.getParameterType().getName())) {
            return true;
        }

        return returnType.hasMethodAnnotation(ResponseBody.class);

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
