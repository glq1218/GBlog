package com.glq1218.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Link)表实体类
 *
 * @author glq1218
 * @since 2022-08-29 19:38:41
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("g_link")
public class Link {

    private Long id;

    private String name;

    private String logo;

    private String description;

    private String address;
    //审核状态（0 代表审核通过，1 代表审核未通过，2 代表未审核）
    private String status;

    //创建人的用户id
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（1 代表删除，0 代表未删除）
    private Integer delFlag;
}

