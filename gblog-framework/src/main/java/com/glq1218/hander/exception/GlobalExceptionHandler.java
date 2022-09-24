package com.glq1218.hander.exception;

import com.glq1218.domain.ResponseResult;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//捕获controller层的异常
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 系统异常
     *
     * @param e 系统异常
     * @return ResponseResult
     */
    @ExceptionHandler(SystemException.class)
    public ResponseResult<?> servicesExceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("发生了系统异常! 原因是：{}", e.getMsg(), e);
        // 从异常对象中获取提示信息封装返回
        return ResponseResult.error(e.getCode(), e.getMsg());
    }


    /**
     * 未知异常
     *
     * @param e 未知异常
     * @return ResponseResult
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<?> exceptionHandler(Exception e) {
        log.error("发生业务异常! {}", e.getMessage(), e);
        return ResponseResult.error(ExceptionEnum.ERROR.getCode(), e.getMessage());
    }

    /**
     * 认证异常
     *
     * @param authException 认证异常
     * @Description: 将 AuthenticationException 异常往上抛，让认证处理器去处理
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public void accountExpiredExceptionHandler(AuthenticationException authException) {
        throw authException;
    }

    /**
     * 授权异常
     *
     * @param accDenException 授权异常
     * @Description: 将 AccessDeniedException 异常往上抛，让授权处理器去处理
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public void accessDeniedExceptionHandler(AccessDeniedException accDenException) {
        throw accDenException;
    }
}
