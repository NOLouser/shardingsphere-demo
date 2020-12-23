package com.nolouser.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

public class StaticDepUserInfoService {

    private static DepUserInfoService depUserInfoService;

    @Autowired
    public void setDepUserInfoService(DepUserInfoService depUserInfoService) {
        StaticDepUserInfoService.depUserInfoService = depUserInfoService;
    }

    public static DepUserInfoService getDepUserInfoService() {
        return depUserInfoService;
    }
}
