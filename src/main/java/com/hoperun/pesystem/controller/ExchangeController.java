package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.ExchangeService;
import com.hoperun.pesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @PostMapping("/allGoods/exchange")
    public  ResultDto<?> showGoodsListBy(@RequestParam(value = "goodsId")     Integer goodsId,
                                    @RequestParam(value = "goodsNum")   Integer goodsNum,
                                    @RequestParam (value = "goodsIntegral") Integer goodsIntegral,
                                    @RequestParam (value = "goodsName")  String goodsName,
                                    HttpServletRequest request){

       User user = userService.getUserByToken( request.getHeader("token"));
       boolean flag = userService.userIntegralChangedByExchangeGoods(goodsIntegral,goodsNum,user.getUserId(),user.getUserIntegral());
        boolean flag1 = exchangeService.excahngeInfoWithGoods(goodsId,goodsIntegral,goodsNum,user.getUserId(),goodsName);
      if (flag && flag1 ){
       return ResultDto.okOf(CustomizeCode.GOODS_EXCHANGE_OK);
      }
      return ResultDto.errorOf(CustomizeCode.INTEGRAL_NOT_ENOUGH);
    }
}
