package com.hoperun.pesystem.service;
import com.hoperun.pesystem.mapper.ExchangeMapper;
import com.hoperun.pesystem.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class ExchangeService {
    @Autowired
    private ExchangeMapper exchangeMapper;
    public boolean excahngeInfoWithGoods(int goodsId ,int goodsIntegral,int goodsNum,int userId,String goodsName){
        Exchange exchange =new Exchange();
        Date date =new Date(System.currentTimeMillis());
        exchange.setExGoodsId(goodsId);
        exchange.setExGoodsIntegral(goodsIntegral);
        exchange.setExGoodsName(goodsName);
        exchange.setExNum(goodsNum);
        exchange.setExUserId(userId);
        exchange.setExTime(date);
        return  exchangeMapper.insert(exchange)==1;
    }

}
