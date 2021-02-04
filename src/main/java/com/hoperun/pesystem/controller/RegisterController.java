package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    @ResponseBody
    public ResultDto<?> register(@RequestParam("phonenumber") String phonenumber,
                              @RequestParam("name") String name,
                              @RequestParam("password") String password
                          ) {
        if(!StringUtils.isNotBlank(name)) return ResultDto.errorOf(CustomizeCode.USERNAME_EMPTY);
        if(!StringUtils.isNotBlank(phonenumber)) return ResultDto.errorOf(CustomizeCode.PHONENUMBER_EMPTY);
        if(!StringUtils.isNotBlank(password)) return ResultDto.errorOf(CustomizeCode.PASSWORD_EMPTY);
        if(!registerService.checkUserName(name)) return ResultDto.errorOf(CustomizeCode.USERNAME_ERROR);
        if(!registerService.checkPhonenumber(phonenumber))  return ResultDto.errorOf(CustomizeCode.PHONENUMBER_ERROR);
        if(!registerService.checkPassword(password)) return ResultDto.errorOf(CustomizeCode.PASSWORD_ERROR);
        if(registerService.register(name,password,phonenumber)){
           return  ResultDto.okOf(CustomizeCode.REGISTER_SUCCESS);
        }
        return ResultDto.errorOf(CustomizeCode.REGISTER_FAILED);
    }
}
