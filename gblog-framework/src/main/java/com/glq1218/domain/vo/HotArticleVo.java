package com.glq1218.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午3:40
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    // id
    private Long id;
    // 标题
    private String title;
    // 访问量
    private Long viewCount;
}
