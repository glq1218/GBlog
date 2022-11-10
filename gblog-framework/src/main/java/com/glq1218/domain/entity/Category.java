package com.glq1218.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Category)表实体类
 *
 * @author glq1218
 * @since 2022-08-28 16:38:30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("g_category")
public class Category {
    
    private Long id;
    //分类名
    private String name;
    //父分类id，-1表示没有父分类
    private Long pid;
    //描述
    private String description;
    //状态：0 正常，1 禁用
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

