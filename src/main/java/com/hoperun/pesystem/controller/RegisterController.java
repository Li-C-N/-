package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.exception.CustomizeErrorCode;
import com.hoperun.pesystem.service.RegisterService;
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
       if(registerService.register(name,password,phonenumber)){
           return  ResultDto.RegisterOk();
       }
        return ResultDto.errorOf(CustomizeErrorCode.REGISTER_FAILED);
    }
}
