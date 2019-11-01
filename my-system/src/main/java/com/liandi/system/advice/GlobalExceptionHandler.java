package com.liandi.system.advice;

import java.util.List;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.liandi.system.exception.SystemException;
import com.liandi.system.response.ResponseDTO;
import com.liandi.system.response.ResponseEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czg
 * @date 2019/7/15 16:43
 * @description 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseDTO handleUnauthorizedException(UnauthorizedException e) {
        return new ResponseDTO(ResponseEnum.SHIRO_UNAUTHC_ERROR);
    }

    @ExceptionHandler(ShiroException.class)
    public ResponseDTO handleShiroException(ShiroException e) {
        log.error("鉴权或授权过程出错：", e);
        return new ResponseDTO(ResponseEnum.SHIRO_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseDTO systemExceptionHandler(SystemException e) {
        return new ResponseDTO(e.getErrorMessage(), e.getCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.error("请求体缺失", e);
        return new ResponseDTO(ResponseEnum.HTTP_MESSAGE_NOT_READABLE_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数校验没通过", e);

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        errors.forEach(error -> errorMsg.append(error.getDefaultMessage()).append(";"));

        return new ResponseDTO(errorMsg.toString(), ResponseEnum.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO exceptionHandler(Exception e) {
        log.error("处理错误：", e);
        return new ResponseDTO(ResponseEnum.ERROR);
    }

}
