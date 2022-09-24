package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.Link;

/**
 * (Link)表服务接口
 *
 * @author glq1218
 * @since 2022-08-29 19:38:41
 */
public interface LinkService extends IService<Link> {

    ResponseResult<?> getAllLink();
}

