package com.nolouser.demo;

import com.google.common.collect.Sets;
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
        String value = "9131012008623224XR";
        BoundZSetOperations<String, Object> zSetOperations = redisTemplate.boundZSetOps("dep:dispatch:lock:condition");
        zSetOperations.range(0, -1).forEach(key->{
            String temp = (String) key;
            if (temp.contains(value)) {
                System.out.println();
                System.out.println(value);

                /*// 移除筛选锁
                zSetOperations.remove(value);*/
            }
        });

    }

    @Test
    public void keys() {
        String matchKey = "*:9131012008623224XR*";
        Set<String> result = redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keys = Sets.newHashSet();
            // Cursor无需释放，RedisTemplate已经做了连接释放
            Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(matchKey).count(10000).build());
            while (cursor.hasNext()) {
                String queueName = new String(cursor.next());
                keys.add(queueName);
            }
            return keys;
        });

        result.forEach(System.out::println);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void list() {
        BoundListOperations<String, Object> boundListOperations = redisTemplate.boundListOps("dep:dispatch:task:SELENIUM_0:DEFAULT:FTSP_KH_SW_JCXX:31");
        boundListOperations.range(0,-1).forEach(System.out::println);
    }


}
