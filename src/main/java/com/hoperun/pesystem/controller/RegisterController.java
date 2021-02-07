package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "注册Controller")
@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;


    @ApiOperation("用户注册")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "phonenumber", value = "手机号码", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String" ),
                    @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
            }
    )


    @PostMapping("/register")
    @ResponseBody
    public ResultDto<?> register(@RequestParam("phonenumber") String phonenumber,
                              @RequestParam("userName") String userName,
                              @RequestParam("password") String password
                          ) {
        if(!StringUtils.isNotBlank(userName)) return ResultDto.errorOf(CustomizeCode.USERNAME_EMPTY);
        if(!StringUtils.isNotBlank(phonenumber)) return ResultDto.errorOf(CustomizeCode.PHONENUMBER_EMPTY);
        if(!StringUtils.isNotBlank(password)) return ResultDto.errorOf(CustomizeCode.PASSWORD_EMPTY);
        if(!registerService.checkUserName(userName)) return ResultDto.errorOf(CustomizeCode.USERNAME_ERROR);
        if(!registerService.checkPhonenumber(phonenumber))  return ResultDto.errorOf(CustomizeCode.PHONENUMBER_ERROR);
        if(!registerService.checkPassword(password)) return ResultDto.errorOf(CustomizeCode.PASSWORD_ERROR);
        if(registerService.register(userName,password,phonenumber)){
           return  ResultDto.okOf(CustomizeCode.REGISTER_SUCCESS);
        }
        return ResultDto.errorOf(CustomizeCode.REGISTER_FAILED);
    }
}
