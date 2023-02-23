package com.nolouser.demo;

import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.*;
import com.nolouser.demo.entity.TOrder;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
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

    @Test
    public void hashMapMemoryLeak() {
        HashMap<TOrder, String> hashMap = new HashMap<>();

        TOrder tOrder = new TOrder();
        tOrder.setStatus("1");
        tOrder.setOrderId(10000L);
        System.out.println(tOrder.hashCode());
        hashMap.put(tOrder, "hello, world!");
        tOrder.setStatus("2");
        System.out.println(tOrder.hashCode());
        System.out.println(hashMap.get(tOrder));

        HashMap<String, String> stringHashMap = new HashMap<>();
        String myStr = "Hello, world!";
        System.out.println(myStr.hashCode());
        stringHashMap.put(myStr, "Hello, world!");
        myStr = "Hello, world1!";
        System.out.println(myStr.hashCode());
        System.out.println(stringHashMap.get("Hello, world!"));
    }


    @Test
    public void testBuildTreeMap() {
        List<String> reachableList = Arrays.asList("dep:dispatch:task:NOLOUSER_RPA.PIT_0:T_pit.91440101054524037P:SBZTCX:44", "dep:dispatch:task:NOLOUSER_RPA.PIT_0:T_pit.914401010611413416:SBZTCX:44");
        Map<String, Object> resultMap = buildTreeMap(reachableList);

        System.out.println(resultMap);

        Map<String, Object> reverseMap = overturnMap((Map<String, Object>)((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) resultMap.get("dep")).get("dispatch")).get("task")).get("NOLOUSER_RPA.PIT_0"));

        System.out.println(reverseMap);

    }

    public Map<String, Object> buildTreeMap(List<String> reachableList) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> nowMap;
        for (String queueName : reachableList) {
            String[] nodeNames = queueName.split(":");
            nowMap = resultMap;
            for (int i = 0; i < nodeNames.length - 1; i++) {
                String nodeName = nodeNames[i];
                Object nodeObj = nowMap.get(nodeName);
                if (nodeObj == null && i != nodeNames.length - 2) {
                    Map<String, Object> swap = new HashMap<>();
                    nowMap.put(nodeName, swap);
                    nowMap = swap;
                } else if (nodeObj != null && i != nodeNames.length - 2) {
                    nowMap = (Map<String, Object>) nodeObj;
                } else if (nodeObj == null && i == nodeNames.length - 2) {
                    List<String> leaf = new ArrayList<>();
                    leaf.add(nodeNames[nodeNames.length - 1]);
                    nowMap.put(nodeName, leaf);
                } else {
                    List<String> leaf = (List<String>) nodeObj;
                    Objects.requireNonNull(leaf).add(nodeNames[nodeNames.length - 1]);
                }
            }
        }
        return resultMap;
    }

    public Map<String, Object> overturnMap(Map<String, Object> map) {
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        map.forEach((key, value) -> {
            Map<String, Object> areaMap = (Map<String, Object>) value;
            areaMap.forEach((key1, value1) -> {
                List<String> value11 = (List<String>) value1;
                String taskType = value11.get(0);
                Object obj = resultMap.get(taskType);
                if (obj == null) {
                    Map<String, Object> temp = new ConcurrentHashMap<>();
                    List<String> list1 = new CopyOnWriteArrayList<>();
                    list1.add(key);
                    temp.put(key1, list1);
                    resultMap.put(taskType, temp);
                } else {
                    Map<String, Object> objectMap = (Map<String, Object>) obj;
                    Object o = objectMap.get(key1);
                    if (o == null) {
                        List<String> list1 = new CopyOnWriteArrayList<>();
                        list1.add(key);
                        objectMap.put(key1, list1);
                    } else {
                        List<String> o1 = (List<String>) o;
                        o1.add(key);
                        objectMap.put(key1, o1);
                    }
                }
            });
        });
        return resultMap;
    }


    @Test
    public void randomWeight() {
        Map<Integer, Integer> result = new HashMap<>();

        List<WeightRandom.WeightObj<Integer>> weightRandoms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            weightRandoms.add(new WeightRandom.WeightObj<>(i, 1));
        }

        WeightRandom<Integer> stringWeightRandom = RandomUtil.weightRandom(weightRandoms);
        for (int i = 0; i < 10000; i++) {
            Integer randomInt = stringWeightRandom.next();
            result.put(randomInt, result.getOrDefault(randomInt, 0)+1);
        }

        System.out.println(result);

    }


}
