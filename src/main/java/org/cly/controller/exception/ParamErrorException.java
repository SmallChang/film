package org.cly.controller.exception;

import lombok.Data;

/**
 * Vo参数校验异常
 */
@Data
public class ParamErrorException extends Exception{

    private Integer code;

    private String errMsg;


    public ParamErrorException(Integer code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
