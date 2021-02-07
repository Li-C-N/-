package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.mapper.ActivityMapper;
import com.hoperun.pesystem.mapper.TypeMapper;
import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.model.ActivityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private TypeMapper typeMapper;
    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:37
     * @description: 获取活动分页列表
     **/
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
    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:37
     * @description: 获取活动详情
     **/
    public Activity queryActivityDetailsById(Integer activityId){
        ActivityExample activityExample=new ActivityExample();
        ActivityExample.Criteria  criteria = activityExample.createCriteria();
        criteria.andAIdEqualTo(activityId);
        List<Activity> activity = activityMapper.selectByExample(activityExample);
        return activity.get(0);
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:39
     * @description: 检查活动是否存在
     **/
    public boolean activityExist(Integer activityId){
        return activityMapper.selectByPrimaryKey(activityId)==null;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:27
     * @description: 检查活动类型是否存在
     **/
    public boolean activityTypeIdExist(Integer goodsTypeId){
        return  typeMapper.selectByPrimaryKey(goodsTypeId)==null;
    }
}
