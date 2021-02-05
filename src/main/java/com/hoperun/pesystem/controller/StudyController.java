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
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

@Controller
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired
    private UserService userService;
    @GetMapping("/allStudy")
    @ResponseBody
    public ResultDto<PageInfo<Study>> showActivityListByPage(@RequestParam(value = "studyTypeId") Integer studyTypeId,
                                                                @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){
//      学堂分页列表
        if(!studyService.studyTypeIdExist(studyTypeId)) {
            PageInfo<Study> pageInfo = studyService.queryStudyByPage(pageNum, pageSize, studyTypeId);
            return ResultDto.okWithData(CustomizeCode.STUDY_PAGEINFO_REQUEST_OK, pageInfo);
        }
        return ResultDto.errorOf(CustomizeCode.STUDY_TYPE_NOT_EXIST);
    }
    @GetMapping("/allStudy/studyDetailsId={studyId}")
    @ResponseBody
    public  ResultDto<Study> showStudyDeatilsById(@PathVariable("studyId") Integer studyId,
                                                  HttpServletRequest request){
        User user = userService.getUserByToken( request.getHeader("token"));
        if(studyId==null) {
            studyId=1;

        }
        if(!studyService.studyExist(studyId)) {
//      返回学堂详情信息且增加学堂浏览记录
            if (studyService.userBrowseRecord(studyId, user.getUserId())) {
//          学堂浏览数+1
                if (studyService.addStudyBrowseRecordCount(studyId)) {
                    return ResultDto.okWithData(CustomizeCode.STUDY_DETAILS_REQUEST_OK_AND_RECORD_OK, studyService.queryStudyDetailsById(studyId));
                }
            }
//      返回学堂详情信息且浏览记录已存在
            return ResultDto.okWithData(CustomizeCode.STUDY_DETAILS_REQUEST_OK_AND_RECORD_EXIST, studyService.queryStudyDetailsById(studyId));
        }
        return ResultDto.errorOf(CustomizeCode.STUDY_NOT_EXIST);
    }

    @PostMapping("/praiseStudy")
    @ResponseBody
    public ResultDto<?> praise (@RequestParam("flag") Integer flag,
                                              @RequestParam("studyId") Integer studyId,
                                              HttpServletRequest request){
        User user = userService.getUserByToken( request.getHeader("token"));

        if(flag==1){
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
