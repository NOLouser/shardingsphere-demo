package com.nolouser.demo.controller;

import com.nolouser.demo.service.impl.TOrderServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class NolouserController {

    @Resource
    private TOrderServiceImpl tOrderService;

    @PostMapping("/hello")
    public Object hello() {
        return tOrderService.hello();
    }

}
