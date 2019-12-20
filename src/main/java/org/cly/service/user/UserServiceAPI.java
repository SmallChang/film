package org.cly.service.user;

import org.cly.controller.user.vo.EnrollUserVO;
import org.cly.controller.user.vo.UserInfoVO;
import org.cly.service.common.exception.CommonServiceException;

public interface UserServiceAPI {

    /**
     * 用户登记接口
     * @param userVO
     * @throws CommonServiceException
     */
    void userEnroll(EnrollUserVO userVO) throws CommonServiceException;

    /**
     * 验证用户是否存在
     * @param userName
     * @return
     * @throws CommonServiceException
     */
    boolean checkUserName(String userName) throws CommonServiceException;

    /**
     * 用户名密码验证
     * @param userName
     * @param userPwd
     * @return
     * @throws CommonServiceException
     */
    boolean userAuth(String userName,String userPwd) throws CommonServiceException;


    /**
     * 获取用户信息
     * @param userId
     * @return
     * @throws CommonServiceException
     */
    UserInfoVO describeUserInfo(String userId) throws CommonServiceException;


    /**
     * 修改用户信息
     * @param userInfoVO
     * @return
     * @throws CommonServiceException
     */
    UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException;

}
