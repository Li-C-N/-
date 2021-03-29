package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private UserMapper userMapper;
    @Autowired
    private ExchangeMapper exchangeMapper;

    /**
     * @Author: ljd
     * @Date: 2021/2/9 13:48
     * @description: 通过phonenumber获取用户信息
     **/
    public List<User> userInfoByPhoneNumber(String PhoneNumber) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserPhoneNumberEqualTo(PhoneNumber);
        return userMapper.selectByExample(userExample);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/9 13:48
     * @description: 用户兑换商品
     **/
    public PageInfo<Exchange> userExchangedByUserId(Integer UserId, Integer pageNum, Integer pageSize) {
        ExchangeExample exchangeExample = new ExchangeExample();
        ExchangeExample.Criteria criteria = exchangeExample.createCriteria();
        criteria.andExUserIdEqualTo(UserId);
        PageHelper.startPage(pageNum, pageSize);
        List<Exchange> exchange = exchangeMapper.selectByExample(exchangeExample);
        return new PageInfo<Exchange>(exchange);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/9 13:49
     * @description: 更新用户积分
     **/
    public boolean userIntegralChangedByExchangeGoods(int goodsIntegral, int goodsNum, int userId, int userIntegral) {
        if (userIntegral >= goodsIntegral * goodsNum) {
            userIntegral = userIntegral - goodsIntegral * goodsNum;
            User user = new User();
            user.setUserId(userId);
            user.setUserIntegral(userIntegral);
            return userMapper.updateByPrimaryKeySelective(user) == 1;
        }
        return false;
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 11:43
     * @description: 通过token得到user对象
     **/
    public User getUserByToken(String token) {
        String PhoneNumber = TokenUtils.getUserphonenumber(token);
        return this.userInfoByPhoneNumber(PhoneNumber).get(0);
    }
}

