package com.hoperun.pesystem.controller;

import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @GetMapping("/allActivity")
    public ResultDto<PageInfo<Activity>> showActivityListByPage(@RequestParam(value = "activityTypeId") Integer activityTypeId,
                                                             @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){

        PageInfo<Activity> pageInfo=activityService.queryActivityByPage(pageNum,pageSize,activityTypeId);
        return  ResultDto.okWithData(CustomizeCode.ACTIVITY_PAGEINFO_REQUEST_OK,pageInfo);
    }
    @GetMapping("/allActivity/activityDetailsId={activityId}")
    public  ResultDto<Activity> showActivityDeatilsById(@PathVariable("activityId") Integer activityId
    ){
        if(activityId==null) {
            activityId=1;
        }
        return  ResultDto.okWithData(CustomizeCode.ACTIVITY_DETAILS_REQUEST_OK,activityService.queryActivityDetailsById(activityId));
    }

}
