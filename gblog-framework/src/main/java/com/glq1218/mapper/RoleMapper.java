package com.glq1218.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glq1218.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author glq1218
 * @since 2022-10-02 00:32:28
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);
}

