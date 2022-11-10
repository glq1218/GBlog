package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.CategoryListDto;
import com.glq1218.domain.vo.CategoryVo;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, CategoryListDto categoryListDto){
        return categoryService.pageCategoryList(pageNum,pageSize,categoryListDto);
    }
    @GetMapping("/listAllCategory")
    public ResponseResult<List<CategoryVo>> listAllCategory(){
        return categoryService.listAllCategory();
    }
}
