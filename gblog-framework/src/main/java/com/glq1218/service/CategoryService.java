package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.CategoryListDto;
import com.glq1218.domain.entity.Category;
import com.glq1218.domain.vo.PageVo;

/**
 * (Category)表服务接口
 *
 * @author glq1218
 * @since 2022-08-28 16:38:30
 */
public interface CategoryService extends IService<Category> {

    ResponseResult<?> getCategoryList();

    ResponseResult<PageVo> pageCategoryList(Integer pageNum, Integer pageSize, CategoryListDto categoryListDto);
}

