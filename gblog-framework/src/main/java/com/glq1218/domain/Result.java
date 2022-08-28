package com.glq1218.domain;

import com.glq1218.enums.ResultInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午1:04
 * @Description: TODO
 */

@Data
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result().codeAndMessage(ResultInfo.SUCCESS);
    }

    public static Result error() {
        Result result = new Result();
        return result;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result message(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result codeAndMessage(Integer code, String message) {
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public Result codeAndMessage(ResultInfo resultInfo) {
        this.setCode(resultInfo.getCode());
        this.setMsg(resultInfo.getMsg());
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

}
