package com.glq1218.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glq1218.domain.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * (Article)表数据库访问层
 *
 * @author glq1218
 * @since 2022-08-28 12:21:27
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}

