package com.hoperun.pesystem.controller;

import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.dto.StudyDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Study;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.StudyService;
import com.hoperun.pesystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Api(tags = "学堂Controller")
@Controller
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired
    private UserService userService;
    /**
     * @Author: ljd
     * @Date: 2021/2/7 8:59
     * @description: 学堂分页列表一览
     **/
    @ApiOperation("学堂类型分页列表")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "studyTypeId", value = "学堂类型id", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "String" ,defaultValue = "1"),
                    @ApiImplicitParam(name = "pageSize", value = "页长", required = true, dataType = "String", defaultValue = "8")
            }
    )
    @GetMapping("/allStudy")
    @ResponseBody
    public ResultDto<PageInfo<StudyDto>> showActivityListByPage(@RequestParam(value = "studyTypeId",defaultValue = "1") Integer studyTypeId,
                                                                @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize,
                                                             HttpServletRequest request){
//      学堂分页列表
        User userInfo = userService.getUserByToken( request.getHeader("token"));

        if(!studyService.studyTypeIdExist(studyTypeId)) {
            PageInfo<StudyDto> pageInfo = studyService.queryStudyByPage(pageNum, pageSize, studyTypeId,userInfo.getUserId());
            return ResultDto.okWithData(CustomizeCode.STUDY_PAGEINFO_REQUEST_OK, pageInfo);
        }
        return ResultDto.errorOf(CustomizeCode.STUDY_TYPE_NOT_EXIST);
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/7 9:00
     * @description: 学堂详情
     **/
    @ApiOperation("学堂详情")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "studyId", value = "活动id", required = true, dataType = "String"),
            }
    )
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
    /**
     * @Author: ljd
     * @Date: 2021/2/7 9:00
     * @description: 学堂点赞
     **/
    @ApiOperation("学堂点赞/取消点赞")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "flag", value = "点赞状态", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "studyId", value = "学堂id", required = true, dataType = "String"),
            }
    )
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
