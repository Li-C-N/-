package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.mapper.ActivityMapper;
import com.hoperun.pesystem.mapper.StudyMapper;
import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.model.ActivityExample;
import com.hoperun.pesystem.model.Study;
import com.hoperun.pesystem.model.StudyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyService {
    @Autowired
    private StudyMapper studyMapper;
    public PageInfo<Study> queryStudyByPage(Integer pageNum, Integer pageSize, Integer type) {
        StudyExample studyExample=new StudyExample();
        StudyExample.Criteria  criteria = studyExample.createCriteria();
        criteria.andStuTypeIdEqualTo(type);
        criteria.andStuFlagEqualTo(0);
        PageHelper.startPage(pageNum,pageSize);
        List<Study> study = studyMapper.selectByExample(studyExample);
        PageInfo<Study> pageInfo = new PageInfo<Study>(study);
        return pageInfo;
    }
}
