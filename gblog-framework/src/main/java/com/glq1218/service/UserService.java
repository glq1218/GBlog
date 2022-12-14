package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.UserListDto;
import com.glq1218.domain.entity.User;
import com.glq1218.domain.vo.PageVo;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author glq1218
 * @since 2022-09-24 22:24:41
 */
public interface UserService extends IService<User> {

    ResponseResult<?> userInfo();

    ResponseResult<?> register(User user);

    ResponseResult<PageVo> pageUserList(Integer pageNum, Integer pageSize, UserListDto userListDto);

    ResponseResult<?> delete(List<Long> ids);
}

