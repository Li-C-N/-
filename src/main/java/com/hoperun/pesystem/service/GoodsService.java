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
    @Autowired
    private TypeMapper typeMapper;

    /**
     * @Author: ljd
     * @Date: 2021/2/5 8:54
     * @description: 获取商品分页信息
     **/
    public PageInfo<Goods> queryGoodsByPage(Integer pageNum, Integer pageSize, Integer goodsTypeId) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsTypeIdEqualTo(goodsTypeId);
        criteria.andGoodsFlagEqualTo(0);
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods);
        return pageInfo;

    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 8:54
     * @description: 按积分排序获取商品分页列表  升序
     **/
    public PageInfo<Goods> queryGoodsByPageWithIntegralDesc(Integer pageNum, Integer pageSize) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsFlagEqualTo(0);
        goodsExample.setOrderByClause("goods_integral desc");
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> pageInfoWithIntegralDesc = new PageInfo<Goods>(goods);
        return pageInfoWithIntegralDesc;
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 8:55
     * @description: 按积分排序获取商品分页列表  降序
     **/
    public PageInfo<Goods> queryGoodsByPageWithIntegralAsc(Integer pageNum, Integer pageSize) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsFlagEqualTo(0);
        goodsExample.setOrderByClause("goods_integral asc");
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> pageInfoWithIntegralAsc = new PageInfo<Goods>(goods);
        return pageInfoWithIntegralAsc;
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 8:55
     * @description: 获取商品详情信息
     **/
    public Goods queryGoodsDetailsById(Integer goodsId) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods.get(0);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:26
     * @description: 根据商品编号获取商品对象
     **/
    public Goods queryGoodsByGoodsId(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:27
     * @description: 检查商品是否存在
     **/
    public boolean goodsIdExist(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId) == null;
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:27
     * @description: 检查商品类型是否存在
     **/
    public boolean goodsTypeIdExist(Integer goodsTypeId) {
        return typeMapper.selectByPrimaryKey(goodsTypeId) == null;
    }

}
