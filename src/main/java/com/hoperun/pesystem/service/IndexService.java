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

    /**
     * @Author: ljd
     * @Date: 2021/2/9 13:45
     * @description: 主页最新活动
     **/
    public List<Activity> showHotInfoWithActivity() {
        ActivityExample activityExample = new ActivityExample();
        ActivityExample.Criteria criteria = activityExample.createCriteria();
        criteria.andAFlagEqualTo(3);
        activityExample.setOrderByClause("a_start_time desc");
        activityExample.setStratNum(1);
        activityExample.setEndNum(3);
        return activityMapper.selectByExample(activityExample);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/9 13:46
     * @description: 主页热门商品
     **/
    public List<Goods> showHotInfoWithGoods() {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsFlagEqualTo(3);
        goodsExample.setOrderByClause("goods_integral desc");
        goodsExample.setStratNum(1);
        goodsExample.setEndNum(3);
        return goodsMapper.selectByExample(goodsExample);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/9 13:46
     * @description: 主页热门学堂
     **/
    public List<Study> showHotInfoWithStudy() {
        StudyExample studyExample = new StudyExample();
        StudyExample.Criteria criteria = studyExample.createCriteria();
        criteria.andStuFlagEqualTo(3);
        studyExample.setOrderByClause("stu_browse_number desc");
        studyExample.setStratNum(1);
        studyExample.setEndNum(3);
        return studyMapper.selectByExample(studyExample);
    }
}
