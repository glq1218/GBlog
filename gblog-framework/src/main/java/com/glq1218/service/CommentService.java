package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.AddCommentDto;
import com.glq1218.domain.entity.Comment;

/**
 * (Comment)表服务接口
 *
 * @author glq1218
 * @since 2022-09-24 14:08:26
 */
public interface CommentService extends IService<Comment> {

    ResponseResult<?> commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult<?> addComment(AddCommentDto commentDto);
}

