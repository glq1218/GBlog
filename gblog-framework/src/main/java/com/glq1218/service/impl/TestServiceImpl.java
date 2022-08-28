package com.glq1218.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.mapper.TestMapper;
import com.glq1218.domain.entity.Test;
import com.glq1218.service.TestService;
import org.springframework.stereotype.Service;

/**
 * (Test)表服务实现类
 *
 * @author glq1218
 * @since 2022-08-28 12:16:57
 */
@Service("testService")
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}

