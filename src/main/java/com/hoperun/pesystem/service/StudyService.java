package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.mapper.BrowseMapper;
import com.hoperun.pesystem.mapper.PraiseMapper;
import com.hoperun.pesystem.mapper.StudyMapper;
import com.hoperun.pesystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyService {
    @Autowired
    private StudyMapper studyMapper;
    @Autowired
    private BrowseMapper browseMapper;
    @Autowired
    private PraiseMapper praiseMapper;
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
    public Study queryStudyDetailsById(Integer studyId){
        StudyExample studyExample=new StudyExample();
        StudyExample.Criteria  criteria = studyExample.createCriteria();
        criteria.andStuIdEqualTo(studyId);
        List<Study> study = studyMapper.selectByExample(studyExample);
        return study.get(0);
    }
//  新增用户浏览记录
    public Boolean userBrowseRecord(Integer studyId,Integer userId){
        if(!userBrowseRecordIsExist(studyId,userId)){
            Browse browse =new Browse();
            browse.setBrowseUserId(userId);
            browse.setBrowseTypeId(studyId);
            return browseMapper.insert(browse)==1;
        }
        return false;
    }
//  用户浏览记录是否存在
    public Boolean userBrowseRecordIsExist(Integer studyId,Integer userId){
        Browse browse =new Browse();
        BrowseExample browseExample=new BrowseExample();
        BrowseExample.Criteria  criteria = browseExample.createCriteria();
        criteria.andBrowseTypeIdEqualTo(studyId);
        criteria.andBrowseUserIdEqualTo(userId);
        return browseMapper.insert(browse)==1;
    }
//  浏览数+1
    public Boolean addStudyBrowseRecordCount(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        study.setStuBrowseNumber(quaryStudyBrowseNum(studyId)+1);
        return studyMapper.updateByPrimaryKeySelective(study)==1;
    }
//  获取浏览数
    public Integer quaryStudyBrowseNum(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        return studyMapper.selectByPrimaryKey(studyId).getStuBrowseNumber();
    }


//  取消点赞删除记录
    public Boolean praiseCancel(Integer studyId,Integer userId){
        Praise Praise =new Praise();
        PraiseExample praiseExample=new PraiseExample();
        PraiseExample.Criteria  criteria = praiseExample.createCriteria();
        criteria.andPraiseTypeIdEqualTo(studyId);
        criteria.andPraiseUserIdEqualTo(userId);
        return praiseMapper.deleteByExample(praiseExample)==1;
    }

//  点赞新增记录
    public Boolean praise(Integer studyId,Integer userId){
        Praise Praise =new Praise();
        Praise.setPraiseUserId(userId);
        Praise.setPraiseTypeId(studyId);
        return praiseMapper.insert(Praise)==1;
    }

//  点赞数+1
    public Boolean addStudyPraiseRecordCount(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        study.setStuBrowseNumber(quaryStudyPraiseNum(studyId)+1);
        return studyMapper.updateByPrimaryKeySelective(study)==1;
    }
//  点赞数-1
    public Boolean subStudyPraiseRecordCount(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        study.setStuBrowseNumber(quaryStudyPraiseNum(studyId)-1);
        return studyMapper.updateByPrimaryKeySelective(study)==1;
    }
//  获取点赞数
    public Integer quaryStudyPraiseNum(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        return studyMapper.selectByPrimaryKey(studyId).getStuPraiseNumber();
    }

}
