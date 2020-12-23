package com.nolouser.demo.service;

import com.nolouser.demo.entity.DepTaskInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepTaskInfoServiceTest {

    @Autowired
    private DepTaskInfoService depTaskInfoService;

    @Test
    void queryById(){
        DepTaskInfo depTaskInfo=depTaskInfoService.getById("00c7e5f15e71cd63e176e26ff6281ad5");
        System.out.println(depTaskInfo);
        Assertions.assertNotNull(depTaskInfo,"depTaskInfo is not null!");

        DepTaskInfo staticDepTaskInfo=StaticDepTaskInfoService.getDepTaskInfoService().getById("00c7e5f15e71cd63e176e26ff6281ad5");
        System.out.println(staticDepTaskInfo);
        Assertions.assertSame(depTaskInfoService,StaticDepTaskInfoService.getDepTaskInfoService());
    }

}
