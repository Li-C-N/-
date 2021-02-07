package com.hoperun.pesystem.controller;

import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Api(tags = "活动Controller")
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @ApiOperation("活动类型分页列表")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "activityTypeId", value = "活动类型id", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "String" ,defaultValue = "1"),
                    @ApiImplicitParam(name = "pageSize", value = "页长", required = true, dataType = "String", defaultValue = "8")
            }
    )
    @GetMapping("/allActivity")
    @ResponseBody
    public ResultDto<PageInfo<Activity>> showActivityListByPage(@RequestParam(value = "activityTypeId") Integer activityTypeId,
                                                             @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){
       if(!activityService.activityTypeIdExist(activityTypeId)) {
           PageInfo<Activity> pageInfo = activityService.queryActivityByPage(pageNum, pageSize, activityTypeId);
           return ResultDto.okWithData(CustomizeCode.ACTIVITY_PAGEINFO_REQUEST_OK, pageInfo);
       }
       return ResultDto.errorOf(CustomizeCode.ACTIVITY_TYPE_NOT_EXIST);
    }
    @ApiOperation("活动详情")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "String"),
            }
    )
    @GetMapping("/allActivity/activityDetailsId={activityId}")
    @ResponseBody
    public  ResultDto<Activity> showActivityDeatilsById(@PathVariable("activityId") Integer activityId
    ){
        if(activityId==null) {
            activityId=1;
        }
        if(!activityService.activityExist(activityId)){
        return  ResultDto.okWithData(CustomizeCode.ACTIVITY_DETAILS_REQUEST_OK,activityService.queryActivityDetailsById(activityId));
        }
        return ResultDto.errorOf(CustomizeCode.ACTIVITY_NOT_EXIST);
    }

}

