package org.cly.service.common.exception;

import lombok.Data;

@Data
public class CommonServiceException extends Exception{
    private Integer code;

    private String errMsg;


    public CommonServiceException(Integer code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

}
