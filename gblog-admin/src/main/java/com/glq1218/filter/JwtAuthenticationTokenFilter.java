package com.glq1218.filter;

import com.alibaba.fastjson.JSON;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.LoginUser;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.util.JwtUtils;
import com.glq1218.util.RedisCache;
import com.glq1218.util.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 2. 解析token
        Claims claims;
        try {
            claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            WebUtils.renderString(response, JSON.toJSONString(ResponseResult.error(ExceptionEnum.TOKEN_VALIDATE_FAILED)));
            return;
        }
        String userId = claims.get("userId").toString();

        // 4. 封装Authentication
        LoginUser loginUser = redisCache.getCacheObject("admin:login:" + userId);

        if (Objects.isNull(loginUser)) {
            WebUtils.renderString(response, JSON.toJSONString(ResponseResult.error(ExceptionEnum.USER_NOT_LOGIN)));
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        // 5. 存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }
}
