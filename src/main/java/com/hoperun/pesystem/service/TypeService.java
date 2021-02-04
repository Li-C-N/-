package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.TypeMapper;
import com.hoperun.pesystem.model.Type;
import com.hoperun.pesystem.model.TypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeService {
    @Autowired
    private TypeMapper typeMapper;
    public List<Type> allGoodsType()
    {
        TypeExample typeExample =new TypeExample();
        TypeExample.Criteria criteria =typeExample.createCriteria();
        criteria.andTypeFlagEqualTo(1);
        return  typeMapper.selectByExample(typeExample);
    }
    public List<Type> allActivityType()
    {
        TypeExample typeExample =new TypeExample();
        TypeExample.Criteria criteria =typeExample.createCriteria();
        criteria.andTypeFlagEqualTo(2);
        return  typeMapper.selectByExample(typeExample);
    }
    public List<Type> allStudyType()
    {
        TypeExample typeExample =new TypeExample();
        TypeExample.Criteria criteria =typeExample.createCriteria();
        criteria.andTypeFlagEqualTo(3);
        return  typeMapper.selectByExample(typeExample);
    }
}
