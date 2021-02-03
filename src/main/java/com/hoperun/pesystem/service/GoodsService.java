package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.mapper.GoodsMapper;
import com.hoperun.pesystem.mapper.TypeMapper;
import com.hoperun.pesystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


    public PageInfo<Goods> queryGoodsByPage(Integer pageNum, Integer pageSize,Integer type) {
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria  criteria = goodsExample.createCriteria();
        criteria.andGoodsTypeIdEqualTo(type);
        criteria.andGoodsFlagEqualTo(0);
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods);
        return pageInfo;

    }
    public PageInfo<Goods> queryGoodsByPageWithIntegralDesc(Integer pageNum, Integer pageSize) {
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria  criteria = goodsExample.createCriteria();
        criteria.andGoodsFlagEqualTo(0);
        goodsExample.setOrderByClause("Integral dasc");
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> pageInfoWithIntegralDesc = new PageInfo<Goods>(goods);
        return pageInfoWithIntegralDesc;
    }
    public PageInfo<Goods> queryGoodsByPageWithIntegralAsc(Integer pageNum, Integer pageSize) {
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria  criteria = goodsExample.createCriteria();
        criteria.andGoodsFlagEqualTo(0);
        goodsExample.setOrderByClause("Integral asc");
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> pageInfoWithIntegralAsc = new PageInfo<Goods>(goods);
        return pageInfoWithIntegralAsc;
    }

    public Goods queryGoodsDetailsById(Integer goodsId){
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria  criteria = goodsExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods.get(0);
    }


}
