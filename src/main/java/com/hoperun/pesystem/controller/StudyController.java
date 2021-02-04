package com.hoperun.pesystem.controller;

import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.model.Study;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.StudyService;
import com.hoperun.pesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired
    private UserService userService;
    @GetMapping("/allStudy")
    public ResultDto<PageInfo<Study>> showActivityListByPage(@RequestParam(value = "studyTypeId") Integer studyTypeId,
                                                                @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){
//      学堂分页列表
        PageInfo<Study> pageInfo=studyService.queryStudyByPage(pageNum,pageSize,studyTypeId);
        return  ResultDto.okWithData(CustomizeCode.STUDY_PAGEINFO_REQUEST_OK,pageInfo);
    }
    @GetMapping("/allStudy/studyDetailsId={studyId}")
    public  ResultDto<Study> showStudyDeatilsById(@PathVariable("studyId") Integer studyId,
                                                  HttpServletRequest request){
        User user = userService.getUserByToken( request.getHeader("token"));
        if(studyId==null) {
            studyId=1;
        }
//      返回学堂详情信息且增加学堂浏览记录
        if(studyService.userBrowseRecord(studyId, user.getUserId())){
//          学堂浏览数+1
            if(studyService.addStudyBrowseRecordCount(studyId)) {
                return ResultDto.okWithData(CustomizeCode.STUDY_DETAILS_REQUEST_OK_AND_RECORD_OK, studyService.queryStudyDetailsById(studyId));
            }
        }

//      返回学堂详情信息且浏览记录已存在
        return  ResultDto.okWithData(CustomizeCode.STUDY_DETAILS_REQUEST_OK_AND_RECORD_EXIST,studyService.queryStudyDetailsById(studyId));
    }

    @GetMapping("/praiseStudy/{studyId}")
    public ResultDto<?> praise (@RequestParam(value = "falg") Integer falg,
                                              @PathVariable("studyId") Integer studyId,
                                              HttpServletRequest request){
        User user = userService.getUserByToken( request.getHeader("token"));

        if(falg==1){
            if (studyService.praise(studyId, user.getUserId())) {
                if (studyService.addStudyPraiseRecordCount(studyId)) {
                    return ResultDto.okOf(CustomizeCode.STUDY_PRAISE_OK);
                }
            }
            return ResultDto.okOf(CustomizeCode.STUDY_PRAISE_FAILED);
        }
       else {
            if (studyService.praiseCancel(studyId, user.getUserId())){
                if(studyService.subStudyPraiseRecordCount(studyId)){
                return   ResultDto.okOf(CustomizeCode.STUDY_PRAISE_CANCEL_OK);
                }
            }
            return ResultDto.okOf(CustomizeCode.STUDY_PRAISE_CANCEL_FAILED);
        }

    }


}
