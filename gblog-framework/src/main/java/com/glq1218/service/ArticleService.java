package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.Result;
import com.glq1218.domain.entity.Article;

/**
 * (Article)表服务接口
 *
 * @author glq1218
 * @since 2022-08-28 12:21:26
 */
public interface ArticleService extends IService<Article> {

    Result hotArticleList();
}
