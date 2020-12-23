package com.nolouser.demo.service;

import com.nolouser.demo.entity.DepUserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DepUserInfoServiceTest {

    @Autowired
    private DepUserInfoService depUserInfoService;

    @Test
    public void query(){
        List<DepUserInfo> depUserInfos=depUserInfoService.list();
        System.out.println(depUserInfos);

        List<DepUserInfo> staticDepUserInfos= StaticDepUserInfoService.getDepUserInfoService().list();
        System.out.println(staticDepUserInfos);

        Assertions.assertSame(depUserInfoService, StaticDepUserInfoService.getDepUserInfoService());
    }

}
