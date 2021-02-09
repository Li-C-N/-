package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.model.UserExample;
import com.hoperun.pesystem.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

/**
 * @Author: ljd
 * @Date: 2021/2/9 13:46
 * @description: 登录check
 **/
    public boolean checkLogin(String phonenumber, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUserPhoneNumberEqualTo(phonenumber);
        List<User> users = userMapper.selectByExample(example);
        if(users.size() == 1){
            User user = users.get(0);
            password = CodecUtils.md5Hex(password);
            return StringUtils.equals(user.getUserPassword(), password);
        }
        return false;
    }
}
