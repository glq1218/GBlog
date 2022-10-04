package com.glq1218.service;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.entity.User;

public interface AdminLoginService {
    ResponseResult<?> login(User user);

    ResponseResult<?> logout();
}
