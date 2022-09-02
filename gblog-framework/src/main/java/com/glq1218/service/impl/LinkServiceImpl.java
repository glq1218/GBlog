package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.constants.SystemConstants;
import com.glq1218.domain.Result;
import com.glq1218.domain.vo.LinkVo;
import com.glq1218.mapper.LinkMapper;
import com.glq1218.domain.entity.Link;
import com.glq1218.service.LinkService;
import com.glq1218.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Link)表服务实现类
 *
 * @author glq1218
 * @since 2022-08-29 19:38:41
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public Result getAllLink() {
        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(lambdaQueryWrapper);
        // 转换为VO
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return Result.success().data(linkVos);
    }
}

