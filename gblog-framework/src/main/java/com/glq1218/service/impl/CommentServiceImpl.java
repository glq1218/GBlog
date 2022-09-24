package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.constants.SystemConstants;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.vo.CommentVo;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.mapper.CommentMapper;
import com.glq1218.domain.entity.Comment;
import com.glq1218.service.CommentService;
import com.glq1218.util.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author glq1218
 * @since 2022-09-24 14:08:26
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public ResponseResult<?> commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        // 对 articleId 进行判断
        lambdaQueryWrapper.eq(Comment::getArticleId, articleId);
        // 根评论 root_id 为 -1
        lambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_ID);
        // 分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(page.getRecords(), CommentVo.class);

        return ResponseResult.success(new PageVo(commentVoList,page.getTotal()));
    }
}

