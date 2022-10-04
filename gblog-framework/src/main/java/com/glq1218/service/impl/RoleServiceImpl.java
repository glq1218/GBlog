package com.glq1218.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.mapper.RoleMapper;
import com.glq1218.domain.entity.Role;
import com.glq1218.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author glq1218
 * @since 2022-10-02 00:32:28
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        List<String> roleKeys = new ArrayList<>();
        if (id == 1) {
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}

