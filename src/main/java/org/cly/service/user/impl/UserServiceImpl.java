package org.cly.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cly.common.utils.MD5Util;
import org.cly.common.utils.ToolUtils;
import org.cly.controller.user.vo.EnrollUserVO;
import org.cly.controller.user.vo.UserInfoVO;
import org.cly.dao.entity.NextUserT;
import org.cly.dao.mapper.NextUserTMapper;
import org.cly.service.common.exception.CommonServiceException;
import org.cly.service.user.UserServiceAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserServiceAPI {


    @Autowired
    NextUserTMapper nextUserTMapper;

    @Override
    public void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException {
        //1、逻辑层校验【业务校验】

        //2、组装NextUserT 转换
        NextUserT nextUserT =new NextUserT();
        //反射 变量名一致
        BeanUtils.copyProperties(enrollUserVO,nextUserT);
        nextUserT.setUserName(enrollUserVO.getUsername());
        nextUserT.setUserPwd(MD5Util.encrypt(enrollUserVO.getPassword()));

        //3、insert
        int isSuccess = nextUserTMapper.insert(nextUserT);
        if(isSuccess != 1){
            throw new CommonServiceException(501,"用户登记失败！");
        }
    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceException {
        Optional.ofNullable(userName).orElseThrow(()->new CommonServiceException(502,"参数错误"));

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        int hasUserName = nextUserTMapper.selectCount(queryWrapper);

        return hasUserName == 0 ? false : true;
    }


    @Override
    public boolean userAuth(String userName, String userPwd) throws CommonServiceException {

        if(ToolUtils.isEmpty(userName) || ToolUtils.isEmpty(userPwd) ){
            throw new CommonServiceException(400,"参数错误，用户登记失败");
        }

        //1、判断用户名是否存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        List<NextUserT> nextUserTList = nextUserTMapper.selectList(queryWrapper);

        if(CollectionUtils.isEmpty(nextUserTList)){
            return false;
        }else {
            //2、如果存在则判断密码是否正确
            // 对用户输入的密码进行md5加密 然后判断两个密码是否相等
            NextUserT nextUserT = nextUserTList.get(0);
            if(Objects.equals(nextUserT.getUserPwd(),MD5Util.encrypt(userPwd))){
                return true;
            }
        }
        return false;
    }

    @Override
    public UserInfoVO describeUserInfo(String userId) throws CommonServiceException {

        NextUserT nextUserT = nextUserTMapper.selectById(userId);
        if(nextUserT != null && nextUserT.getUuid() != null){
            UserInfoVO userInfoVO = user2InfoVO(nextUserT);
            return userInfoVO;
        }else{
            throw new CommonServiceException(404,"用户编号为【"+userId+"】的用户不存在");
        }
    }

    @Override
    public UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException {
        NextUserT nextUserT = info2User(userInfoVO);
        if(nextUserT != null && nextUserT.getUuid() != null){
            int isSuccess = nextUserTMapper.updateById(nextUserT);
            if(isSuccess == 1){
                UserInfoVO result = describeUserInfo(userInfoVO.getUuid() + "");
                return result;
            }else{
                throw new CommonServiceException(500,"用户信息修改失败");
            }
        }else{
            throw new CommonServiceException(404,"用户编号为【"+userInfoVO.getUuid()+"】的用户不存在");
        }
    }

    private UserInfoVO user2InfoVO(NextUserT nextUserT){
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUsername(nextUserT.getUserName());
        userInfoVO.setNickname(nextUserT.getNickName());

        userInfoVO.setBeginTime(nextUserT.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setUpdateTime(nextUserT.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));

        userInfoVO.setLifeState(nextUserT.getLifeState()+"");

        BeanUtils.copyProperties(nextUserT,userInfoVO);
        return userInfoVO;
    }

    private NextUserT info2User(UserInfoVO userInfoVO){
        NextUserT nextUserT = new NextUserT();

        nextUserT.setUserName(userInfoVO.getUsername());
        nextUserT.setNickName(userInfoVO.getNickname());

        nextUserT.setUpdateTime(LocalDateTime.now());

        if(Optional.ofNullable(userInfoVO.getLifeState()).isPresent()) {
            //最好用正则表达式进行判断
            nextUserT.setLifeState(Integer.parseInt(userInfoVO.getLifeState()));
        }
        BeanUtils.copyProperties(userInfoVO,nextUserT);
        return nextUserT;
    }
}
