package org.cly.controller.user;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.cly.common.utils.ToolUtils;
import org.cly.controller.common.BaseResponseVO;
import org.cly.controller.common.TraceUtil;
import org.cly.controller.exception.NextFilmException;
import org.cly.controller.exception.ParamErrorException;
import org.cly.controller.user.vo.EnrollUserVO;
import org.cly.controller.user.vo.UserInfoVO;
import org.cly.service.common.exception.CommonServiceException;
import org.cly.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nextfilm/user/")
@Api("用户模块相关的api")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    /**
     * 用户名验证
     * @param username
     * @return
     * @throws CommonServiceException
     * @throws NextFilmException
     */
    @ApiOperation(value = "用户名重复性验证",notes = "用户名重复性验证")
    @ApiImplicitParam(name = "username",value = "待验证的用户名",paramType = "query",required = true,dataType = "string")
    @RequestMapping(value = "check",method = RequestMethod.POST)
    public BaseResponseVO checkUserName(String username) throws CommonServiceException, NextFilmException {
        if(ToolUtils.isEmpty(username)){
            throw new NextFilmException(1,"username不能为null");
        }

        boolean hasUserName = userServiceAPI.checkUserName(username);
        if(hasUserName){
            return BaseResponseVO.serviceFailed("用户名已存在");
        }else {
            return BaseResponseVO.success();
        }
    }

    /**
     * 用户注册
     * @param enrollUserVO
     * @return
     * @throws CommonServiceException
     * @throws NextFilmException
     * @throws ParamErrorException
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public BaseResponseVO register(@RequestBody EnrollUserVO enrollUserVO) throws CommonServiceException, ParamErrorException {
        //领域模型 贫血模型（VO自己校验）  充血模型
        enrollUserVO.checkParam();
        userServiceAPI.userEnroll(enrollUserVO);
        return BaseResponseVO.success();
    }

    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public BaseResponseVO describeUserInfo() throws CommonServiceException, ParamErrorException {

        String userId = TraceUtil.getUserId();
        if(StringUtils.isEmpty(userId)){
            throw new ParamErrorException(400,"参数错误");
        }

        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);

        userInfoVO.checkParam();

        return BaseResponseVO.success(userInfoVO);
    }

    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(@RequestBody UserInfoVO userInfoVO) throws CommonServiceException, ParamErrorException {

        userInfoVO.checkParam();

        UserInfoVO result = userServiceAPI.updateUserInfo(userInfoVO);

        result.checkParam();

        return BaseResponseVO.success(result);
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(){
        String userId = TraceUtil.getUserId();
        //1、用户信息放入redis
        //1、token失效 2、去掉用户缓存
        return BaseResponseVO.success("");
    }


}
