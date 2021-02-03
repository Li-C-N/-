package com.hoperun.pesystem.controller;

import com.github.pagehelper.PageInfo;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Study;
import com.hoperun.pesystem.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class StudyController {
    @Autowired
    private StudyService studyService;
    @GetMapping("/allStudy")
    public ResultDto<PageInfo<Study>> showActivityListByPage(@RequestParam(value = "studyType") Integer studyType,
                                                                @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize" ,defaultValue = "8") Integer pageSize){

        PageInfo<Study> pageInfo=studyService.queryStudyByPage(pageNum,pageSize,studyType);
        return  ResultDto.okWithData(CustomizeCode.STUDY_PAGEINFO_REQUEST_OK,pageInfo);
    }
}
