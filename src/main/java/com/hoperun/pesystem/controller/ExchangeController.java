package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.ExchangeService;
import com.hoperun.pesystem.service.GoodsService;
import com.hoperun.pesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/allGoods/exchange")
    @ResponseBody
    public ResultDto<?> showGoodsListBy(@RequestParam(value = "goodsId") Integer goodsId,
                                        @RequestParam(value = "goodsNum") Integer goodsNum,
                                        HttpServletRequest request) {

        User user = userService.getUserByToken(request.getHeader("token"));
        if (!goodsService.goodsIdExist(goodsId)) {
            Goods goodsInfo = goodsService.queryGoodsByGoodsId(goodsId);
            if (userService.userIntegralChangedByExchangeGoods(goodsInfo.getGoodsIntegral(), goodsNum, user.getUserId(), user.getUserIntegral())) {
                exchangeService.excahngeInfoWithGoods(goodsId, goodsInfo.getGoodsIntegral(), goodsNum, user.getUserId(), goodsInfo.getGoodsName());
                return ResultDto.okOf(CustomizeCode.GOODS_EXCHANGE_OK);
            }
            return ResultDto.errorOf(CustomizeCode.INTEGRAL_NOT_ENOUGH);

        }
        return ResultDto.errorOf(CustomizeCode.GOODS_NOT_EXIST);
    }
}