package com.glq1218.hander.security;

import com.alibaba.fastjson.JSON;
import com.glq1218.domain.ResponseResult;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.util.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        // InsufficientAuthenticationException 为携带token
        // InternalAuthenticationServiceException 用户名错误
        // BadCredentialsException 密码错误

        ResponseResult<?> result = null;
        if (authException instanceof InternalAuthenticationServiceException) {
            result = ResponseResult.error(ExceptionEnum.USER_ACCOUNT_NOT_EXIST);
        } else if (authException instanceof BadCredentialsException) {
            result = ResponseResult.error(ExceptionEnum.USERNAME_PASSWORD_ERROR);
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.error(ExceptionEnum.USER_NOT_LOGIN);
        } else {
            result  =ResponseResult.error(ExceptionEnum.USER_NOT_LOGIN);
        }
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
