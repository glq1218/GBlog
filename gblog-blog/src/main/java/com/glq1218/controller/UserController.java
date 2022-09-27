package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.User;
import com.glq1218.domain.vo.UserInfoVo;
import com.glq1218.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<?> userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult<?> userInfo(@RequestBody UserInfoVo userInfoVo){
//        return userService.userInfo();
    return ResponseResult.error("我没写这个功能,你改个屁呢");
    }

    @PostMapping("/register")
    public ResponseResult<?> register(@RequestBody User user){
        return userService.register(user);
    }
}
