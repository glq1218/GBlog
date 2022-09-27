package com.glq1218.domain;

import com.glq1218.enums.ExceptionEnum;
import com.glq1218.exception.SystemException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应对象
 */
@Data
@AllArgsConstructor
@ApiModel
public class ResponseResult<T> implements Serializable {
    /**
     * 是否成功
     */
    @ApiModelProperty("是否成功 true 成功 false 失败")
    private Boolean success;

    /**
     * 响应枚举类
     */
    private ExceptionEnum exceptionEnum;
    /**
     * 返回状态码
     */
    @ApiModelProperty(value = "返回状态码")
    private Integer code;
    /**
     * 返回信息
     */
    @ApiModelProperty("返回信息")
    private String msg;
    /**
     * 响应数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    public ResponseResult(Boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Boolean success, ExceptionEnum exceptionEnum) {
        this.success = success;
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }

    public static <T> ResponseResult<?> success() {
        return new ResponseResult<>(true, 200, "success");
    }

    public static <T> ResponseResult<?> success(T data) {
        return new ResponseResult<>(true, 200, "success", data);
    }

    public static <T> ResponseResult<?> success(String msg, T data) {
        return new ResponseResult<>(true, 200, msg, data);
    }

    public static <T> ResponseResult<?> error(SystemException systemException) {
        return new ResponseResult<>(false, systemException.getCode(), systemException.getMsg());
    }

    public static <T> ResponseResult<?> error(int code, String msg) {
        return new ResponseResult<>(false, code, msg);
    }

    public static <T> ResponseResult<?> error(String msg) {
        return new ResponseResult<>(false, 999, msg);
    }

    public static <T> ResponseResult<?> error(ExceptionEnum exceptionEnum) {
        return new ResponseResult<>(false, exceptionEnum);
    }

}
