package org.cly.controller.user.vo;

import lombok.Data;
import org.cly.controller.common.BaseVO;
import org.cly.controller.exception.ParamErrorException;

@Data
public class UserInfoVO extends BaseVO {
    private Integer id;
    private Integer uuid;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private Integer sex;
    private String birthday;
    private String lifeState;
    private String biography;
    private String address;
    private String headAddress;
    //使用包装类型 利于判断
    private Long beginTime;
    private Long updateTime;

    public Integer getUuid(){
        return this.getId();
    }

    @Override
    public void checkParam() throws ParamErrorException {
        //todo 自己加入验证逻辑

    }
}
