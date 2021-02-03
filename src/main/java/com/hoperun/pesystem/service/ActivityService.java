package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.mapper.ActivityMapper;
import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.model.ActivityExample;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.model.GoodsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    public PageInfo<Activity> queryActivityByPage(Integer pageNum, Integer pageSize,Integer type) {
        ActivityExample activityExample=new ActivityExample();
        ActivityExample.Criteria  criteria = activityExample.createCriteria();
        criteria.andATypeEqualTo(type);
        criteria.andAFlagEqualTo(0);
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> activity = activityMapper.selectByExample(activityExample);
        PageInfo<Activity> pageInfo = new PageInfo<Activity>(activity);
        return pageInfo;
    }
}
