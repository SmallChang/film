package org.cly.service.user.impl;

import org.cly.FilmApplication;
import org.cly.controller.user.vo.EnrollUserVO;
import org.cly.controller.user.vo.UserInfoVO;
import org.cly.service.common.exception.CommonServiceException;
import org.cly.service.user.UserServiceAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @Test
    public void userEnroll() throws CommonServiceException {
        EnrollUserVO enrollUserVO = new EnrollUserVO();
        enrollUserVO.setUsername("lfy");
        enrollUserVO.setPassword("123456");
        enrollUserVO.setEmail("1@qq.com");
        enrollUserVO.setAddress("1");
        enrollUserVO.setPhone("12345677645");
        userServiceAPI.userEnroll(enrollUserVO);
    }

    @Test
    public void checkUserName() {
    }

    @Test
    public void userAuth() {
    }

    @Test
    public void describeUserInfo() throws CommonServiceException {
        String userId="2";
        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);
        System.out.println(userInfoVO);
    }

    @Test
    public void updateUserInfo() throws CommonServiceException {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUuid(4);
        userInfoVO.setUsername("test");
        userInfoVO.setLifeState("1");

        UserInfoVO userInfoVO1 = userServiceAPI.updateUserInfo(userInfoVO);
        System.out.println(userInfoVO1);
    }
}