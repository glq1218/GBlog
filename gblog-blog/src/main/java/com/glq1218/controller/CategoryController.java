package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午4:46
 * @Description: TODO
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult<?> getCategoryList(){
        return categoryService.getCategoryList();
    }
}
