package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Type;
import com.hoperun.pesystem.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;
    @PostMapping("/allGoodsType")
    @ResponseBody
    public ResultDto<List<Type>> allGoodsType(){
        return ResultDto.okWithData(CustomizeCode.GOODS_TYPE_REQUEST_OK,typeService.allGoodsType());
    }
    @PostMapping("/allActivityType")
    @ResponseBody
    public ResultDto<List<Type>> allActivityType(){
        return ResultDto.okWithData(CustomizeCode.ACTIVITY_TYPE_REQUEST_OK,typeService.allActivityType());
    }
    @PostMapping("/allStudyType")
    @ResponseBody
    public ResultDto<List<Type>> allStudyType(){
        return ResultDto.okWithData(CustomizeCode.STUDY_TYPE_REQUEST_OK,typeService.allStudyType());
    }
}
