package com.glq1218.service.impl;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.LoginUser;
import com.glq1218.domain.entity.User;
import com.glq1218.domain.vo.BlogLoginUserVo;
import com.glq1218.domain.vo.UserInfoVo;
import com.glq1218.service.BlogLoginService;
import com.glq1218.util.BeanCopyUtils;
import com.glq1218.util.JwtUtils;
import com.glq1218.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<?> login(User user) {

        // 获取authenticate
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 生成token
        String token = JwtUtils.generateToken(userId);
        // 把用户信息存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);

        // 把token和userInfo封装vo返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogLoginUserVo vo = new BlogLoginUserVo(token, userInfoVo);
        return ResponseResult.success(vo);
    }

    @Override
    public ResponseResult<?> logout() {
        // 获取token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //解析获取id
        Long userId = loginUser.getUser().getId();
        // 删除redis中的用户信息
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.success();
    }
}
