package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.Article;

/**
 * (Article)表服务接口
 *
 * @author glq1218
 * @since 2022-08-28 12:21:26
 */
public interface ArticleService extends IService<Article> {

    ResponseResult<?> hotArticleList();

    ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult<?> getArticleDetail(Long id);

    ResponseResult<?> updateViewCount(Long id);
}

