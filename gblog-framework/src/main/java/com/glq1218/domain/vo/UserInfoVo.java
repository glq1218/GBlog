package com.glq1218.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserInfoVo {
    //主键
    private Long id;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;
}
