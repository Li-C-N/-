package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.LoginService;
import com.hoperun.pesystem.service.RegisterService;
import com.hoperun.pesystem.utils.TokenUtils;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录Controller")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;

    @ApiOperation("用户登录")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "phonenumber", value = "手机号码", required = true, dataType = "String"),
                    @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
            }
    )
    @PostMapping("/login")
    @ResponseBody
    public ResultDto<?> login(String phonenumber,
                              String password
    ) {
        System.out.println(phonenumber);
        System.out.println(password);
        if (!StringUtils.isNotBlank(phonenumber)) {
            return ResultDto.errorOf(CustomizeCode.PHONENUMBER_EMPTY);
        }
        if (!StringUtils.isNotBlank(password)) {
            return ResultDto.errorOf(CustomizeCode.PASSWORD_EMPTY);
        }
        if (!registerService.checkPhonenumber(phonenumber)) {
            return ResultDto.errorOf(CustomizeCode.PHONENUMBER_ERROR);
        }
        if (!registerService.registered(phonenumber)) {
            return ResultDto.errorOf(CustomizeCode.PHONENUMBER_REGISTER_ERROR);
        }
        if (loginService.checkLogin(phonenumber, password)) {
            String token = TokenUtils.sign(phonenumber);
            return ResultDto.okWithData(CustomizeCode.LOGIN_SUCCESS, token);
        }
        return ResultDto.errorOf(CustomizeCode.LOGIN_FAILED);
    }
}
