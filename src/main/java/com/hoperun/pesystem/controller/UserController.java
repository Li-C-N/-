package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.UserService;
import com.hoperun.pesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService  userService;
         @PostMapping("/personInfo")
         @ResponseBody
       public ResultDto PersosnInfo( HttpServletRequest request)
        {
            String PhoneNumber = TokenUtils.getUserphonenumber( request.getHeader("token"));
          return ResultDto.okOf(userService.UserInfoByPhoneNumber(PhoneNumber));
        }
    }

