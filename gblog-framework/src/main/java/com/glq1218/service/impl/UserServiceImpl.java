package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.UserListDto;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.domain.vo.UserInfoVo;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import com.glq1218.mapper.UserMapper;
import com.glq1218.domain.entity.User;
import com.glq1218.service.UserService;
import com.glq1218.util.BeanCopyUtils;
import com.glq1218.util.SecurityUtils;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author glq1218
 * @since 2022-09-24 22:24:41
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult<?> userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id查询用户信息
        User user = getById(userId);
        // 封装成userInfoVo返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.success(userInfoVo);
    }

    @Override
    public ResponseResult<?> register(User user) {
        //对数据进行非空判断
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(ExceptionEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(ExceptionEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(ExceptionEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickname())) {
            throw new SystemException(ExceptionEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if (usernameExist(user.getUsername())) {
            throw new SystemException(ExceptionEnum.USER_ACCOUNT_EXISTED);
        }
        if (emailExist(user.getEmail())) {
            throw new SystemException(ExceptionEnum.EMAIL_EXISTED);
        }
        //...
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<PageVo> pageUserList(Integer pageNum, Integer pageSize, UserListDto userListDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(userListDto.getUsername()), User::getUsername, userListDto.getUsername());
        queryWrapper.eq(StringUtils.hasText(userListDto.getPhoneNumber()), User::getPhoneNumber, userListDto.getPhoneNumber());
        queryWrapper.eq(StringUtils.hasText(userListDto.getStatus()), User::getStatus, userListDto.getStatus());
        Page<User> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        return ResponseResult.success(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult<?> delete(List<Long> ids) {
        if (removeByIds(ids)) {
            return ResponseResult.success();
        }
        return ResponseResult.error("删除失败");
    }


    private boolean usernameExist(String attribute) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, attribute);
        return count(queryWrapper) > 0;
    }

    private boolean emailExist(String attribute) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, attribute);
        return count(queryWrapper) > 0;
    }
}

