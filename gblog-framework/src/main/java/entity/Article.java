package entity;

import java.util.Date;

import lombok.Data;

/**
 * (Article)表实体类
 *
 * @author glq1218
 * @since 2022-08-28 01:05:20
 */

@Data
public class Article {

    private Long id;
    //标题
    private String title;
    //文章标题
    private String content;
    //文章类型：1
    private String type;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
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

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（1 代表删除，0 代表未删除）
    private Integer delFlag;
}

