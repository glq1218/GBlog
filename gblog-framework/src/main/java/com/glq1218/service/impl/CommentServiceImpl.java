package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.constants.SystemConstants;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.AddCommentDto;
import com.glq1218.domain.entity.User;
import com.glq1218.domain.vo.CommentVo;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import com.glq1218.mapper.CommentMapper;
import com.glq1218.domain.entity.Comment;
import com.glq1218.service.CommentService;
import com.glq1218.service.UserService;
import com.glq1218.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author glq1218
 * @since 2022-09-24 14:08:26
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult<?> commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 对 articleId 进行判断
        lambdaQueryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);
        // 根评论 root_id 为 -1
        lambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_ID);
        // 评论类型
        lambdaQueryWrapper.eq(Comment::getType, commentType);
        // 按时间排序
        lambdaQueryWrapper.orderByDesc(Comment::getCreateTime);
        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        // 查询所有根评论对应的子评论，并赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> childrenList = getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(childrenList);
        }
        return ResponseResult.success(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult<?> addComment(AddCommentDto commentDto) {
        //评论内容不能为空
        if (!StringUtils.hasText(commentDto.getContent())) {
            throw new SystemException(ExceptionEnum.CONTENT_NOT_NULL);
        }
        Comment comment = BeanCopyUtils.copyBean(commentDto, Comment.class);
        save(comment);
        return ResponseResult.success();
    }

    /**
     * 查询根评论对应的子评论
     *
     * @param id 根评论id
     * @return 子评论集合
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getRootId, id);
        lambdaQueryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> commentList = list(lambdaQueryWrapper);
        return toCommentVoList(commentList);
    }

    /**
     * 封装为commentVo
     *
     * @param commentList 评论列表
     * @return 评论列表vo
     */
    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        // 遍历vo集合
        for (CommentVo commentVo : commentVoList) {
            // 通过createBy查询用户的昵称并赋值
            User user = userService.getById(commentVo.getCreateBy());
            commentVo.setUsername(user.getNickname());
            commentVo.setAvatar(user.getAvatar());
            // 通过toCommentUserId查询用户的昵称并赋值
            User toCommentUser = userService.getById(commentVo.getToCommentUserId());
            if (commentVo.getToCommentUserId() != -1) {
                commentVo.setToCommentUserName(toCommentUser.getNickname());
                commentVo.setToCommentUserAvatar(toCommentUser.getAvatar());
            }
        }
        return commentVoList;
    }
}

