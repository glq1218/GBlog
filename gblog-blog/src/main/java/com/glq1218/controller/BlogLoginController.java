package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.User;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import com.glq1218.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult<?> login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(ExceptionEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult<?> logout() {
        return blogLoginService.logout();
    }

}
