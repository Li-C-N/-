package com.hoperun.pesystem.controller;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.service.GoodsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "商品Controller")
@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
/**
 * @Author: ljd
 * @Date: 2021/2/7 11:01
 * @description: 商品分类列表
 **/
@ApiOperation("商品类型分页列表")
//@ApiImplicitParams：多个请求参数
@ApiImplicitParams(
        value = {
                @ApiImplicitParam(paramType = "path",name = "goodsTypeId", value = "商品类型id", required = true, dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "pageNum", value = "页码", required = true, dataType = "String" ,defaultValue = "1"),
                @ApiImplicitParam(paramType = "query",name = "pageSize", value = "页长", required = true, dataType = "String", defaultValue = "8")
        }
)
    @GetMapping("/allGoods/goodsTypeId={goodsTypeId}")
    @ResponseBody
    public  ResultDto<PageInfo<Goods>> showGoodsListByPage(@PathVariable("goodsTypeId") Integer goodsTypeId,
                                                   @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){
        if(!goodsService.goodsTypeIdExist(goodsTypeId)) {


            PageInfo<Goods> pageInfo = goodsService.queryGoodsByPage(pageNum, pageSize, goodsTypeId);
            return ResultDto.okWithData(CustomizeCode.GOODS_PAGEINFO_REQUEST_OK, pageInfo);
        }
        return ResultDto.errorOf(CustomizeCode.GOODS_TYPE_NOT_EXIST);
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/7 10:59
     * @description: 积分排序列表
     **/
    @ApiOperation("积分排序分页列表")
//@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "integralSort", value = "积分排序", required = true, dataType = "String",defaultValue = "0"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "String" ,defaultValue = "1"),
                    @ApiImplicitParam(name = "pageSize", value = "页长", required = true, dataType = "String", defaultValue = "8")
            }
    )
    @GetMapping("/allGoods")
    @ResponseBody
    public  ResultDto<PageInfo<Goods>> showGoodsListByIntegral(@RequestParam(value = "integralSort" ,defaultValue = "0") Integer integralSort,
                                                           @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize" , defaultValue = "8") Integer pageSize){
        PageInfo<Goods> pageInfoWithIntegralDesc=goodsService.queryGoodsByPageWithIntegralDesc(pageNum,pageSize);
        PageInfo<Goods> pageInfoWithIntegralAsc=goodsService.queryGoodsByPageWithIntegralAsc(pageNum,pageSize);
        if(integralSort==1){

                return  ResultDto.okWithData(CustomizeCode.GOODS_PAGEINFO_WITH_INTEGRAL_DESC_REQUEST_OK,pageInfoWithIntegralDesc);
        }
        return  ResultDto.okWithData(CustomizeCode.GOODS_PAGEINFO_WITH_INTEGRAL_ASC_REQUEST_OK,pageInfoWithIntegralAsc);
    }

/**
 * @Author: ljd
 * @Date: 2021/2/7 11:00
 * @description: 商品详情
 **/
@ApiOperation("商品详情")
//@ApiImplicitParams：多个请求参数
@ApiImplicitParams(
        value = {
                @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "String")
        }
)
    @GetMapping("/allGoods/goodsDetailsId={goodsId}")
    @ResponseBody
    public  ResultDto<Goods> showGoodDeatilsById(@PathVariable("goodsId") Integer goodsId
    ) {
        if (goodsId == null) {
            goodsId = 1;
        }
        if (!goodsService.goodsIdExist(goodsId)) {
            return ResultDto.okWithData(CustomizeCode.GOODS_DETAILS_REQUEST_OK, goodsService.queryGoodsDetailsById(goodsId));
        }
        return ResultDto.errorOf(CustomizeCode.GOODS_NOT_EXIST);
    }

}
