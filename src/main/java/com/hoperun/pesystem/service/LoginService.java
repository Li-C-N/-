package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.model.UserExample;
import com.hoperun.pesystem.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginService {
    @Autowired
    private UserMapper userMapper;

    private static final int COOKIE_EXPIRY = 60 * 60 * 24 * 7;


    public boolean checkLogin(String phonenumber, String password, HttpServletResponse response) {
        UserExample example = new UserExample();
        example.createCriteria().andUserPhoneNumberEqualTo(phonenumber);
        List<User> users = userMapper.selectByExample(example);
        if(users.size() == 1){
            User user = users.get(0);
            password = CodecUtils.md5Hex(password, user.getSalt());

        }
        return false;
    }
}
