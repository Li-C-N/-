package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User user;
    public boolean register(String  username,String password,String phonenumber){

        return  userMapper.insertSelective(User);
    }
}
