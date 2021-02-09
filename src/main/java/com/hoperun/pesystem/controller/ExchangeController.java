package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.ExchangeService;
import com.hoperun.pesystem.service.GoodsService;
import com.hoperun.pesystem.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Api(tags = "兑换Controller")
@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @ApiOperation("商品兑换")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "goodsNum", value = "商品数量", required = true, dataType = "String" ),
            }
    )
    @PostMapping("/allGoods/exchange")
    @ResponseBody
    public ResultDto<?> showGoodsListBy( Integer goodsId,
                                         Integer goodsNum,
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