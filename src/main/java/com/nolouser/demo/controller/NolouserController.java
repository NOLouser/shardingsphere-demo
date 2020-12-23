package com.nolouser.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NolouserController {

    @PostMapping("/hello")
    public Object hello(){
        Map<String,String> result=new HashMap<>();
        result.put("word","hello, world!");

        System.out.println("响应！！！！！！！！");
        return result;
    }

}
