package com.hoperun.pesystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.mapper.BrowseMapper;
import com.hoperun.pesystem.mapper.PraiseMapper;
import com.hoperun.pesystem.mapper.StudyMapper;
import com.hoperun.pesystem.mapper.TypeMapper;
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
    @Autowired
    private TypeMapper typeMapper;
    /**
     * @Author: ljd
     * @Date: 2021/2/7 9:16
     * @description:
     **/
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
    /**
     * @Author: ljd
     * @Date: 2021/2/5 17:16
     * @description: 根据学堂Id获取学堂详情
     **/
    public Study queryStudyDetailsById(Integer studyId){
        StudyExample studyExample=new StudyExample();
        StudyExample.Criteria  criteria = studyExample.createCriteria();
        criteria.andStuIdEqualTo(studyId);
        List<Study> study = studyMapper.selectByExample(studyExample);
        return study.get(0);
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:53
     * @description: 新增用户浏览记录
     **/
    public Boolean userBrowseRecord(Integer studyId,Integer userId){
        if(!userBrowseRecordIsExist(studyId,userId)){
            Browse browse =new Browse();
            browse.setBrowseUserId(userId);
            browse.setBrowseTypeId(studyId);
            return browseMapper.insert(browse)==1;
        }
        return false;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/5 18:05
     * @description: 用户记录是否存在
     **/
    public Boolean userBrowseRecordIsExist(Integer studyId,Integer userId){
        Browse browse =new Browse();
        BrowseExample browseExample=new BrowseExample();
        BrowseExample.Criteria  criteria = browseExample.createCriteria();
        criteria.andBrowseTypeIdEqualTo(studyId);
        criteria.andBrowseUserIdEqualTo(userId);
        return browseMapper.selectByExample(browseExample).size()>0;
    }
   /**
    * @Author: ljd
    * @Date: 2021/2/7 8:54
    * @description: 学堂浏览数+1
    **/
    public Boolean addStudyBrowseRecordCount(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        study.setStuBrowseNumber(quaryStudyBrowseNum(studyId)+1);
        return studyMapper.updateByPrimaryKeySelective(study)==1;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:54
     * @description: 获取当前学堂浏览数
     **/
    public Integer quaryStudyBrowseNum(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        return studyMapper.selectByPrimaryKey(studyId).getStuBrowseNumber();
    }


    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:55
     * @description: 用户取消点赞（数据库删除点赞表记录）
     **/
    public Boolean praiseCancel(Integer studyId,Integer userId){
        Praise Praise =new Praise();
        PraiseExample praiseExample=new PraiseExample();
        PraiseExample.Criteria  criteria = praiseExample.createCriteria();
        criteria.andPraiseTypeIdEqualTo(studyId);
        criteria.andPraiseUserIdEqualTo(userId);
        return praiseMapper.deleteByExample(praiseExample)==1;
    }

/**
 * @Author: ljd
 * @Date: 2021/2/7 8:56
 * @description: 用户点赞（数据库新增点赞表记录）
 **/
    public Boolean praise(Integer studyId,Integer userId){
        Praise Praise =new Praise();
        Praise.setPraiseUserId(userId);
        Praise.setPraiseTypeId(studyId);
        return praiseMapper.insert(Praise)==1;
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:57
     * @description: 学堂点赞数+1
     **/
    public Boolean addStudyPraiseRecordCount(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        study.setStuPraiseNumber(quaryStudyPraiseNum(studyId)+1);
        return studyMapper.updateByPrimaryKeySelective(study)==1;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:57
     * @description: 学堂点赞数-1
     **/
    public Boolean subStudyPraiseRecordCount(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        study.setStuPraiseNumber(quaryStudyPraiseNum(studyId)-1);
        return studyMapper.updateByPrimaryKeySelective(study)==1;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:57
     * @description: 获取当前学堂点赞数
     **/
    public Integer quaryStudyPraiseNum(Integer studyId){
        Study study =new Study();
        study.setStuId(studyId);
        return studyMapper.selectByPrimaryKey(studyId).getStuPraiseNumber();
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:39
     * @description: 检查学堂是否存在
     **/
    public boolean studyExist(Integer studyId){
        return studyMapper.selectByPrimaryKey(studyId)==null;
    }
    /**
     * @Author: ljd
     * @Date: 2021/2/5 9:27
     * @description: 检查学堂类型是否存在
     **/
    public boolean studyTypeIdExist(Integer studyTypeId){
        return  typeMapper.selectByPrimaryKey(studyTypeId)==null;
    }
}
