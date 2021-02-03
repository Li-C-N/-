package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
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
    @PostMapping("/allType")
    public ResultDto<List<Type>> allType(){
        return ResultDto.okOf(typeService.allGoodsType());
    }
}
