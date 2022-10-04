package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.TagListDto;
import com.glq1218.domain.entity.Tag;
import com.glq1218.domain.vo.PageVo;

/**
 * 标签(Tag)表服务接口
 *
 * @author glq1218
 * @since 2022-10-01 22:08:25
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}

