package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.ExchangeMapper;
import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.*;
import com.hoperun.pesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private ExchangeMapper exchangeMapper;
    public List<User> userInfoByPhoneNumber(String PhoneNumber){
        UserExample userExample =new UserExample();
        UserExample.Criteria criteria =userExample.createCriteria();
        criteria.andUserPhoneNumberEqualTo(PhoneNumber);
        return  userMapper.selectByExample(userExample);
    }
    public List<Exchange> userExchangedByUserId(Integer UserId){
        ExchangeExample exchangeExample =new ExchangeExample();
        ExchangeExample.Criteria criteria =exchangeExample.createCriteria();
        criteria.andExUserIdEqualTo(UserId);
        return  exchangeMapper.selectByExample(exchangeExample);
    }
    public boolean userIntegralChangedByExchangeGoods(int goodsIntegral,int goodsNum,int userId,int userIntegral){
        if(userIntegral>=goodsIntegral*goodsNum) {
            userIntegral = userIntegral - goodsIntegral * goodsNum;
            User user = new User();
            user.setUserId(userId);
            user.setUserIntegral(userIntegral);
            return userMapper.updateByPrimaryKeySelective(user) == 1;
        }
        return false;
    }
    public  User getUserByToken(String token) {
        String PhoneNumber = TokenUtils.getUserphonenumber(token);
        return  this.userInfoByPhoneNumber(PhoneNumber).get(0);
    }
}

