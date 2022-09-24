package com.glq1218.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glq1218.domain.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户表(User)表数据库访问层
 *
 * @author glq1218
 * @since 2022-09-23 16:30:42
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}

