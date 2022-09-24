package com.glq1218.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: glq
 * @Data: 2022/9/21 下午4:33
 * @Description: TODO
 */
public class WebUtils {

    public static String renderString(HttpServletResponse response,String string){
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
