package org.cly.controller.auth.controller.vo;

import lombok.Data;
import org.cly.common.utils.ToolUtils;
import org.cly.controller.common.BaseVO;
import org.cly.controller.exception.ParamErrorException;

@Data
public class AuthRequestVO extends BaseVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws ParamErrorException {
        if(ToolUtils.isEmpty(this.getUsername())){
            throw new ParamErrorException(400,"用户名不能为空");
        }
        if(ToolUtils.isEmpty(this.getPassword())){
            throw new ParamErrorException(400,"密码不能为空");
        }
    }
}
