package org.cly.controller.common.handler;

import org.cly.controller.common.BaseResponseVO;
import org.cly.controller.exception.NextFilmException;
import org.cly.controller.exception.ParamErrorException;
import org.cly.service.common.exception.CommonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NextFilmException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseVO nextFilmException(NextFilmException e){
        return BaseResponseVO.serviceFailed(e.getErrMsg());

    }

    @ExceptionHandler(CommonServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseVO commonServiceException(CommonServiceException e){
        return BaseResponseVO.serviceFailed(e.getCode(),e.getErrMsg());
    }

    @ExceptionHandler(ParamErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseVO paramErrorException(ParamErrorException e){
        return BaseResponseVO.serviceFailed(e.getCode(),e.getErrMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseVO exception(Exception e){

        return BaseResponseVO.systemError();
    }

}
