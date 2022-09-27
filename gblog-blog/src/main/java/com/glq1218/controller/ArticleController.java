package com.glq1218.controller;


import com.glq1218.config.SwaggerConfig;
import com.glq1218.domain.ResponseResult;
import com.glq1218.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Article)表控制层
 *
 * @author glq1218
 * @since 2022-08-28 12:19:12
 */
@RestController
@RequestMapping("/article")
@Api(tags = {SwaggerConfig.ARTICLE})
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
    @ApiOperation(value = "热门文章", notes = "获取热门文章的列表")
    public ResponseResult<?> hotArticleList() {
        return articleService.hotArticleList();
    }

    /**
     * 文章列表
     */
    @GetMapping("/articleList")
    @ApiOperation(value = "文章列表", notes = "获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = false),
    })
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * 文章详情
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "文章详情", notes = "获取文章详情")
    public ResponseResult<?> getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("updateViewCount/{id}")
    @ApiOperation(value = "更新文章浏览量", notes = "更新文章的浏览量")
    public ResponseResult<?> updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}