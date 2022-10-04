package com.glq1218.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glq1218.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author glq1218
 * @since 2022-10-02 00:28:37
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}

