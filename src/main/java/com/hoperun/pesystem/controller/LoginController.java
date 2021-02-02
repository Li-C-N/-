package com.hoperun.pesystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.exception.CustomizeErrorCode;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.LoginService;
import com.hoperun.pesystem.utils.TokenUtils;
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
        public ResultDto login(@RequestBody JSONObject jsonObject, @RequestHeader Map<String,Object> he, @RequestBody Map<String,Object> para) throws JsonProcessingException {

            String phonenumber=jsonObject.getString("loginName");
            String password=jsonObject.getString("password");

//            String phonenumber=(String)para.get("phonenumber");
//            String password=(String)para.get("password");
            if (loginService.checkLogin(phonenumber,password)){
//                user.setUserPassword(password);
                String token= TokenUtils.sign(phonenumber);
//                HashMap<String,Object> hs=new HashMap<>();
//                hs.put("token",token+phonenumber+password);
//                ObjectMapper objectMapper=new ObjectMapper();
//                return objectMapper.writeValueAsString(hs);
               return  ResultDto.okOf(token);
            }
            return  ResultDto.errorOf(CustomizeErrorCode.LOGIN_FAILED);
        }


    }
