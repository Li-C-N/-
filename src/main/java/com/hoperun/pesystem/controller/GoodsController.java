package com.hoperun.pesystem.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.model.Type;
import com.hoperun.pesystem.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/allGoods")
    public  ResultDto<PageInfo<Goods>> showGoodsListByPage(@RequestParam("goodsType") int goodsType,
                                                   @RequestParam(defaultValue = "1") int pageNum,
                                                   @RequestParam(defaultValue = "8") int pageSize){

        PageInfo<Goods> pageInfo=goodsService.queryGoodsByPage(pageNum,pageSize,goodsType);
        return  ResultDto.okOf(pageInfo);
    }

    @GetMapping("/allGoods/goodsDetailsId={goodsId}")
    public  ResultDto<Goods> showGoodDeatilsById(@PathVariable("goodsId") int goodsId
    ){
        return  ResultDto.okOf(goodsService.queryGoodsDetailsById(goodsId));
    }


}
