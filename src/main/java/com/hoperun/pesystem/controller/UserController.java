package com.hoperun.pesystem.controller;

import com.hoperun.pesystem.dto.ResultDto;
import com.hoperun.pesystem.enums.CustomizeCode;
import com.hoperun.pesystem.model.Exchange;
import com.hoperun.pesystem.model.User;
import com.hoperun.pesystem.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "个人信息Controller")
@Controller
public class UserController {
    @Autowired
    private UserService  userService;
    @ApiOperation("个人信息中心")
    //@ApiImplicitParams：多个请求参数
    @ApiImplicitParams(
            value = {}
    )
         @PostMapping("/personInfo")
         @ResponseBody
       public ResultDto<?> persosnInfo( HttpServletRequest request)
        {
            User userInfo = userService.getUserByToken( request.getHeader("token"));
            List<Exchange> exchanges = userService.userExchangedByUserId(userInfo.getUserId());
            Map<String,Object> map = new HashMap<>();
            map.put("用户信息",userInfo);
            map.put("商品兑换记录",exchanges);
            return ResultDto.okWithData(CustomizeCode.USERINFO_AND_EXCHANGE_RECORD_REQUEST_OK,map);
        }
    }

