package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glq1218.domain.entity.LoginUser;
import com.glq1218.domain.entity.User;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import com.glq1218.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        // 如果没有查询到用户就抛出异常
        if (Objects.isNull(user)) {
            throw new SystemException(ExceptionEnum.USER_ACCOUNT_NOT_EXIST);
        }
        // TODO 查询对应的权限信息
        List<String> perms = new ArrayList<>(Arrays.asList("test", "demo"));
//        List<String> perms = menuMapper.selectPermsByUserId(user.getId());
        // 把数据分装成UserDetails返回
        return new LoginUser(user, perms);
    }
}
