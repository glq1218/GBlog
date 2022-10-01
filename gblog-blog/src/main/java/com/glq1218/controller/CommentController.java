package com.glq1218.controller;

import com.glq1218.constants.SystemConstants;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.AddCommentDto;
import com.glq1218.domain.entity.Comment;
import com.glq1218.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult<?> commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult<?> addComment(@RequestBody AddCommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

    /**
     * 友链评论接口
     *
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return resp
     */
    @GetMapping("/linkCommentList")
    public ResponseResult<?> commentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }

}
