package com.nolouser.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

public class StaticDepTaskInfoService {

    private static DepTaskInfoService depTaskInfoService;

    @Autowired
    public void setDepTaskInfoService(DepTaskInfoService depTaskInfoService) {
        StaticDepTaskInfoService.depTaskInfoService = depTaskInfoService;
    }

    public static DepTaskInfoService getDepTaskInfoService() {
        return depTaskInfoService;
    }
}
