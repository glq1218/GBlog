package com.glq1218.hander.security;

import com.alibaba.fastjson.JSON;
import com.glq1218.domain.ResponseResult;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        WebUtils.renderString(response, JSON.toJSONString(ResponseResult.error(ExceptionEnum.USER_NO_PERMISSIONS)));
    }
}
