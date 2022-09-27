package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.constants.SystemConstants;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.Article;
import com.glq1218.domain.entity.Category;
import com.glq1218.domain.vo.ArticleDetailVo;
import com.glq1218.domain.vo.ArticleListVo;
import com.glq1218.domain.vo.HotArticleVo;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.mapper.ArticleMapper;
import com.glq1218.service.ArticleService;
import com.glq1218.service.CategoryService;
import com.glq1218.util.BeanCopyUtils;
import com.glq1218.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * (Article)表服务实现类
 *
 * @author glq1218
 * @since 2022-08-28 12:21:27
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<?> hotArticleList() {
        // 查询热门文章,封装成Result
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        lambdaQueryWrapper.orderByDesc(Article::getViewCount);
        // 最多只查询10条
        Page<Article> page = new Page<>(1, 10);

        page(page, lambdaQueryWrapper);

        List<Article> articles = page.getRecords();

        for (Article article : articles) {
            // 从redis中查询浏览量
            Integer viewCount = redisCache.getCacheMapValue("article:viewCount", article.getId().toString());
            article.setViewCount(viewCount.longValue());
        }

        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.success(hotArticleVos);
    }

    @Override
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 是否正式发布
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        // 查询categoryName
        List<Article> articles = page.getRecords();

        // categoryId去查询categoryName进行设置

        // 循环
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
            article.setCategoryId(category.getId());
            // 从redis中查询浏览量
            Integer viewCount = redisCache.getCacheMapValue("article:viewCount", article.getId().toString());
            article.setViewCount(viewCount.longValue());
        }

        //stream流
//        articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());

        // 封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override
    public ResponseResult<?> getArticleDetail(Long id) {
        // 根据id查询文章
        Article article = getById(id);
        // 从redis中查询浏览量
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        // 状换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        // 封装响应返回
        return ResponseResult.success(articleDetailVo);
    }

    @Override
    public ResponseResult<?> updateViewCount(Long id) {
        // 更新redis中对应id文章的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.success();
    }
}

