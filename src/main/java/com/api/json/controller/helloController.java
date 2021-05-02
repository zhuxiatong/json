package com.api.json.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.json.entry.ZXT;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class helloController {

     @RequestMapping("/hello/zz")
     public Object hello(){
          ZXT zxt = new ZXT("zxt","nan",18,"woo");
          return JSONObject.toJSONString(zxt).toString();
     }
     @RequestMapping("/zz")
     public String zz(){
          return "zzz";
     }
}
