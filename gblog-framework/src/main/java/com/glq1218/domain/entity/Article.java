package com.glq1218.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (Article)表实体类
 *
 * @author glq1218
 * @since 2022-08-28 12:21:24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("g_article")
//链式访问
@Accessors(chain = true)
@ApiModel(description = "文章实体类")
public class Article {
    @TableId
    @ApiModelProperty(notes = "主键id")
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章类型：1
    private String type;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    //所属分类名
    @TableField(exist = false)
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0 否，1 是）
    private String isTop;
    //状态(0 已发布，1 草稿）
    private String status;
    //评论数
    private Integer commentCount;
    //访问量
    private Long viewCount;
    //是否允许评论 1 是，0 否
    private String isComment;

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

    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }

}

