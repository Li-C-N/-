package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.ExchangeMapper;
import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.Exchange;
import com.hoperun.pesystem.model.ExchangeExample;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.model.UserExample;
import com.hoperun.pesystem.utils.CodecUtils;
import org.apache.catalina.mapper.Mapper;
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
    public List<User> UserInfoByPhoneNumber(String PhoneNumber){
        UserExample userExample =new UserExample();
        UserExample.Criteria criteria =userExample.createCriteria();
        criteria.andUserPhoneNumberEqualTo(PhoneNumber);
        return  userMapper.selectByExample(userExample);
    }
    public List<Exchange> UserInfoByUserId(Integer UserId){
        ExchangeExample exchangeExample =new ExchangeExample();
        ExchangeExample.Criteria criteria =exchangeExample.createCriteria();
        criteria.andExUserIdEqualTo(UserId);
        return  exchangeMapper.selectByExample(exchangeExample);
    }
}
