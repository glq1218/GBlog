package com.glq1218.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.mapper.LinkMapper;
import com.glq1218.domain.entity.Link;
import com.glq1218.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * (Link)表服务实现类
 *
 * @author glq1218
 * @since 2022-08-29 19:38:41
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

}

