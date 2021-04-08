package com.nolouser.demo;

import com.google.common.collect.*;
import com.google.gson.Gson;
import com.nolouser.demo.entity.TOrder;
import com.nolouser.demo.note.structure.Node;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NolouserTest {

    @Test
    public void overLongNum() {
        BigDecimal overLong = new BigDecimal("10003521543763483263500288202012");
        if (overLong.compareTo(new BigDecimal(Long.MAX_VALUE)) > 0) {
            System.out.println("over LongNum: " + overLong);
        }

        if (new BigDecimal("546703633534681088").compareTo(new BigDecimal(Long.MAX_VALUE)) > 0) {
            System.out.println("over LongNum: 546703633534681088");
        } else {
            System.out.println("normal LongNum: 546703633534681088");
        }
    }

    @Test
    public void printMinu() {
        for (int i = 0; i < 60; i++) {
            System.out.print(String.format("'%02d'", i) + ",");
        }
    }

    @Test
    public void multiMap() {
        Multimap<String, TOrder> multimap = ArrayListMultimap.create();
        TOrder tOrder1 = new TOrder();
        tOrder1.setOrderId(1000000000L);
        tOrder1.setStatus("成功");
        multimap.put("key1", tOrder1);
        TOrder tOrder2 = new TOrder();
        tOrder2.setOrderId(1000000001L);
        tOrder2.setStatus("成功");
        multimap.put("key1", tOrder2);

        multimap.putAll("key2",Arrays.asList(tOrder1,tOrder2));
        System.out.println(multimap);

        // 使用自定义的Multimap
        Multimap<String, TOrder> selfMultimap = Multimaps.newMultimap(Maps.newHashMap(), () -> Lists.newLinkedList());
        multimap.forEach((s, tOrder) -> selfMultimap.put(s, tOrder));
        System.out.println(selfMultimap);

    }

    @Test
    public void stream(){
        List<String> streamList = new ArrayList<>();
        streamList.add("hello, world!");
        streamList.add("hello, xiaoming");
        streamList.add("world");
        streamList.add("xiaoming");
        String regex = "hello.*";
        System.out.println(streamList.stream().filter(s -> {
            boolean matchFlag = Pattern.matches(regex, s);
            System.out.println(matchFlag);
            return matchFlag;
        }).collect(Collectors.toList()));
    }

    @Test
    public void split(){
        String queueName = "dep:dispatch:INTERFACE:37:FTSP_SB_GS_ZZSXGM:1:15017953320";
        String[] splits = queueName.split(":");
        splits[4] = "*";
        System.out.println(String.join(":", splits));
    }


    @Test
    public void exception() {
        String deptName = "dep-agent";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String secretKey = "$1$g.wTjJu1$vPvUqsX28CPuiJOsUsAeZ/";

        System.out.println(timestamp);
        System.out.println(Md5Crypt.md5Crypt((deptName+timestamp).getBytes(),secretKey));

    }

    @Test
    public void mutilMap() {
        Multimap<String, String> multimap = HashMultimap.create();
        multimap.put("logic", "hello");
        multimap.put("logic", "world!");
        System.out.println(multimap);


        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition.signal();

    }

}
