package com.glq1218.controller;



import com.glq1218.domain.Result;
import com.glq1218.domain.entity.Article;
import com.glq1218.enums.ResultInfo;
import com.glq1218.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //@GetMapping("/list")
    //public Result test(){
    //    List<Article> list = articleService.list();
    //    return Result.success().codeAndMessage(ResultInfo.SUCCESS).data("list",list);
    //}

    /**
     * 热门文章列表
     */
    @GetMapping("/hotArticleList")
    public Result hotArticleList(){
        Result result = articleService.hotArticleList();
        return result;
    }
}

