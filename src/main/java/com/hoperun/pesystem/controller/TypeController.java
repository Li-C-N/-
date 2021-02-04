package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Type;
import com.hoperun.pesystem.service.GoodsService;
import com.hoperun.pesystem.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;
    @PostMapping("/allGOODSType")
    public ResultDto<List<Type>> allGOODSType(){
        return ResultDto.okWithData(CustomizeCode.GOODS_TYPE_REQUEST_OK,typeService.allGoodsType());
    }
    @PostMapping("/allActivityType")
    public ResultDto<List<Type>> allActivityType(){
        return ResultDto.okWithData(CustomizeCode.GOODS_TYPE_REQUEST_OK,typeService.allActivityType());
    }
    @PostMapping("/allStudtType")
    public ResultDto<List<Type>> allStudtType(){
        return ResultDto.okWithData(CustomizeCode.GOODS_TYPE_REQUEST_OK,typeService.allStudyType());
    }
}
