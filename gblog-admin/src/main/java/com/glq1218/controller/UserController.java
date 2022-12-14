package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.UserListDto;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, UserListDto userListDto){
        return userService.pageUserList(pageNum,pageSize,userListDto);
    }

    @DeleteMapping("ids")
    public ResponseResult<?> delete(@PathVariable("ids")List<Long> ids){
        return userService.delete(ids);
    }
}
