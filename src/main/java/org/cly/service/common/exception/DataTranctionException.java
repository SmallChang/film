package org.cly.service.common.exception;

import lombok.Data;

@Data
public class DataTranctionException extends Exception {

    private Integer code;

    private String errMsg;


    public DataTranctionException(Integer code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

}
