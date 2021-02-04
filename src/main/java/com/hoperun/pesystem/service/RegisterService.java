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
import java.util.regex.Pattern;

@Service
public class RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Value("${beetle.defaultAvatar}")
    private String defaultAvatar;
    /**
     * @Author: ljd
     * @Date: 2021/2/4 14:22
     * @description: 手机号码正则匹配校验
     **/
    public Boolean checkPhonenumber(String phonenumber) {
        String reg_phonenumber = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
        return Pattern.matches(reg_phonenumber, phonenumber);
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/4 14:40
     * @description: 用户名正则匹配中英文，数字组合
     **/
    public Boolean checkUserName(String userName) {
        String reg_userName = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        return Pattern.matches(reg_userName, userName);
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/4 14:29
     * @description: 密码规则正则匹配（必须包含数字和字母  6-16位长）
     **/
    public Boolean checkPassword(String password) {
        String reg_password = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

        return Pattern.matches(reg_password, password);
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/4 14:23
     * @description: 检测手机号码是否已经注册
     **/
    public Boolean registered(String phonenumber) {
        UserExample example = new UserExample();
        example.createCriteria().andUserPhoneNumberEqualTo(phonenumber);
        List<User> users = userMapper.selectByExample(example);
        return users.size() > 0;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/4 14:23
     * @description: 注册
     **/
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
