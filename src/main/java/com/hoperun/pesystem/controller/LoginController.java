package com.hoperun.pesystem.controller;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.exception.CustomizeErrorCode;
import com.hoperun.pesystem.service.LoginService;
import com.hoperun.pesystem.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
        @PostMapping("/login")
        @ResponseBody
        public ResultDto<?> login(  @RequestParam("phonenumber") String phonenumber,
                                    @RequestParam("password") String password ){
            if (StringUtils.isNotBlank(phonenumber) && StringUtils.isNotBlank(password)) {
                if (loginService.checkLogin(phonenumber,password)){
                    String token= TokenUtils.sign(phonenumber);
                    return  ResultDto.LoginOK(token);
                }
                return  ResultDto.errorOf(CustomizeErrorCode.LOGIN_FAILED);
            }
            return  ResultDto.errorOf(CustomizeErrorCode.LOGIN_ISBLACK);
        }
    }
