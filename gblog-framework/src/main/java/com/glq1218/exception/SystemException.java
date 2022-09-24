package com.glq1218.exception;

import com.glq1218.enums.ExceptionEnum;
import lombok.Data;

@Data
public class SystemException extends RuntimeException {

    private final Integer code;
    private final String msg;

    public SystemException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }

    //接收自定义msg的方式构造业务异常
    public SystemException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
