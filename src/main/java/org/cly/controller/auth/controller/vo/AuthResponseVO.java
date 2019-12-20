package org.cly.controller.auth.controller.vo;

import lombok.Builder;
import lombok.Data;
import org.cly.controller.common.BaseVO;
import org.cly.controller.exception.ParamErrorException;

@Data
@Builder
public class AuthResponseVO extends BaseVO {
    private String randomKey;
    private String token;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
