package com.glq1218.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户列表dto")
public class UserListDto {
    private String username;
    private String phoneNumber;
    private String status;
}
