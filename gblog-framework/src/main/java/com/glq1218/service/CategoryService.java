package com.glq1218.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glq1218.domain.Result;
import com.glq1218.domain.entity.Category;

/**
 * (Category)表服务接口
 *
 * @author glq1218
 * @since 2022-08-28 16:38:30
 */
public interface CategoryService extends IService<Category> {

    Result getCategoryList();
}

