package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.model.UserExample;
import com.hoperun.pesystem.utils.CodecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
@Service
public class RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Value("${beetle.defaultAvatar}")
    private String defaultAvatar;
//    检查是否注册
    public Boolean registered(String phonenumber) {
        UserExample example = new UserExample();
        example.createCriteria().andUserPhoneNumberEqualTo(phonenumber);
        List<User> users = userMapper.selectByExample(example);
        return users.size() > 0;
    }
//    注册
    public boolean register(String  username,String password,String phonenumber) {
        Date date =new Date(System.currentTimeMillis());
        if (!registered(phonenumber)) {
            User record = new User();
            record.setUserPhoneNumber(phonenumber);
            record.setUserIntegral(0);
            record.setUserUsername(username);
            record.setUserBirthday(date);
            record.setUserCaptionUrl(defaultAvatar);
            //对密码加密
            record.setUserPassword(CodecUtils.md5Hex(password));
            return this.userMapper.insertSelective(record) == 1;
        }
        return false;
    }
}
