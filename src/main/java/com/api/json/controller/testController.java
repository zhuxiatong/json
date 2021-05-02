package com.api.json.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "version 10.10.10",tags = "用户管理相关的接口", description = "用户测试接口")
public class testController {
    @RequestMapping("/test")
     public String getHello(int a){
         return "hello"+a;
     }
}
