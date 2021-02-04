package com.hoperun.pesystem.controller;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/allGoods/goodsTypeId={goodsTypeId}")
    @ResponseBody
    public  ResultDto<PageInfo<Goods>> showGoodsListByPage(@PathVariable("goodsTypeId") Integer goodsTypeId,
                                                   @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){

        PageInfo<Goods> pageInfo=goodsService.queryGoodsByPage(pageNum,pageSize,goodsTypeId);
        return  ResultDto.okWithData(CustomizeCode.GOODS_PAGEINFO_REQUEST_OK,pageInfo);
    }
    @GetMapping("/allGoods")
    @ResponseBody
    public  ResultDto<PageInfo<Goods>> showGoodsListByIntegral(@RequestParam(value = "integraSort" ,defaultValue = "0") Integer integraSort,
                                                           @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize" , defaultValue = "8") Integer pageSize){
        PageInfo<Goods> pageInfoWithIntegralDesc=goodsService.queryGoodsByPageWithIntegralDesc(pageNum,pageSize);
        PageInfo<Goods> pageInfoWithIntegralAsc=goodsService.queryGoodsByPageWithIntegralAsc(pageNum,pageSize);
        if(integraSort==1){

                return  ResultDto.okWithData(CustomizeCode.GOODS_PAGEINFO_WITH_INTEGRAL_DESC_REQUEST_OK,pageInfoWithIntegralDesc);
        }
        return  ResultDto.okWithData(CustomizeCode.GOODS_PAGEINFO_WITH_INTEGRAL_ASC_REQUEST_OK,pageInfoWithIntegralAsc);
    }


    @GetMapping("/allGoods/goodsDetailsId={goodsId}")
    @ResponseBody
    public  ResultDto<Goods> showGoodDeatilsById(@PathVariable("goodsId") Integer goodsId
    ){
        if(goodsId==null) {
            goodsId=1;
        }
        return  ResultDto.okWithData(CustomizeCode.GOODS_DETAILS_REQUEST_OK,goodsService.queryGoodsDetailsById(goodsId));
    }


}
