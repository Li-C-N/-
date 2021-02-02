package com.hoperun.pesystem.service;

import com.hoperun.pesystem.mapper.ActivityMapper;
import com.hoperun.pesystem.mapper.GoodsMapper;
import com.hoperun.pesystem.mapper.StudyMapper;
import com.hoperun.pesystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StudyMapper studyMapper;

    public List<Activity> ShowHotInfoWithActivity(){
        ActivityExample  activityExample=new ActivityExample();
        ActivityExample.Criteria  criteria = activityExample.createCriteria();
        criteria.andAFlagEqualTo(3);
        return activityMapper.selectByExample(activityExample);
    }
    public List<Goods> ShowHotInfoWithGoods(){
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria  criteria = goodsExample.createCriteria();
        criteria.andGoodsFlagEqualTo(3);
        return goodsMapper.selectByExample(goodsExample);
    }
    public List<Study> ShowHotInfoWithStudy(){
        StudyExample  studyExample=new StudyExample();
        StudyExample.Criteria  criteria = studyExample.createCriteria();
        criteria.andStuFlagEqualTo(3);
        return studyMapper.selectByExample(studyExample);
    }
}
