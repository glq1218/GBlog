package com.glq1218.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午3:57
 * @Description: TODO
 */
public class BeanCopyUtils {
    public BeanCopyUtils() {
    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            // 实现属性拷贝
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}