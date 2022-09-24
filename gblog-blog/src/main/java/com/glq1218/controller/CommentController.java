package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult<?> commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(articleId,pageNum,pageSize);
    }
}
