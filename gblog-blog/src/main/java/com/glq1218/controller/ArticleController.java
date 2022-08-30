package com.glq1218.controller;


import com.glq1218.domain.Result;
import com.glq1218.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Article)表控制层
 *
 * @author glq1218
 * @since 2022-08-28 12:19:12
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 热门文章列表
     */
    @GetMapping("/hotArticleList")
    public Result hotArticleList() {
        return articleService.hotArticleList();
    }

    /**
     * 文章列表
     */
    @GetMapping("/articleList")
    public Result articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * 文章详情
     */
    @GetMapping("/{id}")
    public Result getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
}