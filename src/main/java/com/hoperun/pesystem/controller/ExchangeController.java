package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.exception.CustomizeErrorCode;
import com.hoperun.pesystem.mapper.UserMapper;
import com.hoperun.pesystem.model.Exchange;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.ExchangeService;
import com.hoperun.pesystem.service.UserService;
import com.hoperun.pesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @GetMapping("/allGoods/exchangeId={goodsId}")
    public  ResultDto<?> showGoodsListBy(@PathVariable("goodsId")    int goodsId,
                                    @RequestParam("goodsNum")   int goodsNum,
                                    @RequestParam ("goodsIntegral") int goodsIntegral,
                                    @RequestParam ("goodsName")  String goodsName,
                                    HttpServletRequest request){

       User user = userService.getUserByToken( request.getHeader("token"));
       boolean flag = userService.userIntegralChangedByExchangeGoods(goodsIntegral,goodsNum,user.getUserId(),user.getUserIntegral());
        boolean flag1 = exchangeService.excahngeInfoWithGoods(goodsId,goodsIntegral,goodsNum,user.getUserId(),goodsName);
      if (flag && flag1 ){
       return ResultDto.okOf("兑换成功!");
      }
      return ResultDto.errorOf(CustomizeErrorCode.INTEGRAL_NOT_ENOUGH);
    }
}
