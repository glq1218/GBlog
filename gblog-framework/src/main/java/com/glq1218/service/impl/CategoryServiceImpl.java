package com.glq1218.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glq1218.constants.SystemConstants;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.CategoryListDto;
import com.glq1218.domain.entity.Article;
import com.glq1218.domain.vo.CategoryVo;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.mapper.CategoryMapper;
import com.glq1218.domain.entity.Category;
import com.glq1218.service.ArticleService;
import com.glq1218.service.CategoryService;
import com.glq1218.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (Category)表服务实现类
 *
 * @author glq1218
 * @since 2022-08-28 16:38:30
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult<?> getCategoryList() {
        // 查询文章表，状态为已发布
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleQueryWrapper);
        // 获取文章的分类id，并且去重
        Set<Long> CategoryId = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        // 查询分类表
        List<Category> categorys = listByIds(CategoryId);

        categorys = categorys.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categorys, CategoryVo.class);

        return ResponseResult.success(categorys);
    }

    @Override
    public ResponseResult<PageVo> pageCategoryList(Integer pageNum, Integer pageSize, CategoryListDto categoryListDto) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(categoryListDto.getName()), Category::getName, categoryListDto.getName());
        queryWrapper.eq(StringUtils.hasText(categoryListDto.getStatus()), Category::getStatus, categoryListDto.getStatus());
        Page<Category> page = new Page<>(pageNum, pageSize);
        page(page);
        return ResponseResult.success(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult<List<CategoryVo>> listAllCategory() {
        List<Category> categories = list();
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.success(categoryVos);
    }
}

