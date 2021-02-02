package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
}
