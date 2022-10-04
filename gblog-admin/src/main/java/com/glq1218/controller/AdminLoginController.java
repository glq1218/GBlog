package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.LoginUser;
import com.glq1218.domain.entity.Menu;
import com.glq1218.domain.entity.User;
import com.glq1218.domain.vo.AdminUserInfoVo;
import com.glq1218.domain.vo.MenuVo;
import com.glq1218.domain.vo.RoutersVo;
import com.glq1218.domain.vo.UserInfoVo;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import com.glq1218.service.AdminLoginService;
import com.glq1218.service.MenuService;
import com.glq1218.service.RoleService;
import com.glq1218.util.BeanCopyUtils;
import com.glq1218.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult<?> login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(ExceptionEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        //1获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //2根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //3根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        // 4 封装数据返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.success(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        // 查询menu 结果是tree的形式
        List<MenuVo> menuVos = menuService.selectRouterMenuTreeByUserId(userId);
        // 封装数据返回
        RoutersVo routersVo = new RoutersVo(menuVos);
        return ResponseResult.success(routersVo);
    }

    @PostMapping("/user/logout")
    public ResponseResult<?> logout() {
        return adminLoginService.logout();
    }

}
