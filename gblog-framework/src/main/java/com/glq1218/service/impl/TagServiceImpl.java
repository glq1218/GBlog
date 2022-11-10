package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.TagListDto;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.mapper.TagMapper;
import com.glq1218.domain.entity.Tag;
import com.glq1218.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author glq1218
 * @since 2022-10-01 22:08:25
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());
        Page<Tag> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        return ResponseResult.success(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult<?> add(Tag tag) {
        if (!save(tag)) {
            return ResponseResult.error("添加失败");
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<?> delete(List<Long> ids) {
        if (removeByIds(ids)) {
            return ResponseResult.success();
        }
        return ResponseResult.error("删除失败");
    }

    @Override
    public ResponseResult<Tag> get(Long id) {
        Tag tag = getById(id);
        return ResponseResult.success(tag);
    }

    @Override
    public ResponseResult<?> edit(Tag tag) {
        if (updateById(tag)) {
            return ResponseResult.success();
        }
        return ResponseResult.error("修改失败");
    }

    @Override
    public ResponseResult<List<Tag>> listAllTag() {
        List<Tag> list = list();
        return ResponseResult.success(list);
    }
}

