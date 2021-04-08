package com.nolouser.demo;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTemplateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTemplateTest.class);

    @Resource
    private StringRedisTemplate myRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private Gson gson;

    @Test
    public void getMembers() {
        Set<String> keys = myRedisTemplate.keys("dep:dispatch:task:INTERFACE_0:DEFAULT:FTSP_SB_DS_ZHSB:*");
        System.out.println(gson.toJson(keys));


    }

    @Test
    public void range() {
        BoundZSetOperations<String, String> zSetOperations = myRedisTemplate.boundZSetOps("www.nolouser.ml.queue");
        /*String prefix = "www.nolouser.ml.no.";
        for (int i = 0; i < 100; i++) {
            String value = prefix + String.format("%03d", i);
            zSetOperations.add(value, i);
        }*/

        Set<String> range = zSetOperations.range(0, -1);
        LOGGER.info("\n\nsize:{}", range.size());
        for (String s : range) {
            LOGGER.info(s);
        }

        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.rangeWithScores(0, -1);
        LOGGER.info("\n\nsize:{}", typedTuples.size());
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            LOGGER.info(typedTuple.getValue()+" : "+typedTuple.getScore());
        }

        Set<String> stringSet = zSetOperations.rangeByScore(0, 100);
        LOGGER.info("\n\nsize:{}", stringSet.size());
        for (String s : stringSet) {
            LOGGER.info(s);
        }

    }

    @Test
    public void zset() {
        BoundZSetOperations<String, Object> zSetOperations = redisTemplate.boundZSetOps("dep:dispatch:lock:condition");
        zSetOperations.range(0, -1).forEach(key->{
            System.out.println(key.toString());
        });

    }

    @Test
    public void keys() {
        Set<String> keys = redisTemplate.keys("*13323789893*");
        keys.forEach(System.out::println);
    }

    @Test
    public void list() {
        BoundListOperations<String, Object> boundListOperations = redisTemplate.boundListOps("dep:dispatch:task:RPA.DEP_0:13323789893:FTSP_SB_ZZS_INFO:41");
        boundListOperations.range(0,-1).forEach(System.out::println);
    }


}
