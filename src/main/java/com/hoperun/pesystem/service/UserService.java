package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.model.UserExample;
import com.hoperun.pesystem.utils.CodecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private  UserMapper userMapper;
    public List<User> UserInfoByPhoneNumber(String PhoneNumber){
        UserExample userExample =new UserExample();
        UserExample.Criteria criteria =userExample.createCriteria();
        criteria.andUserPhoneNumberEqualTo(PhoneNumber);
        return  userMapper.selectByExample(userExample);
    }
}
