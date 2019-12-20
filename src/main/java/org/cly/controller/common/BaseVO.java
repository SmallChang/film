package org.cly.controller.common;

import org.cly.controller.exception.ParamErrorException;

public abstract class BaseVO {

    public abstract void checkParam() throws ParamErrorException;

}
