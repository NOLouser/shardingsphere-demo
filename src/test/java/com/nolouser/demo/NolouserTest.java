package com.nolouser.demo;

import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithm;
import org.apache.shardingsphere.infra.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.infra.spi.typed.TypedSPIRegistry;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Properties;

public class NolouserTest {


    @Test
    public void reporter(TestReporter reporter) {
        reporter.publishEntry("key", "hello, world!");
    }

    @Test
    public void overLongNum(){
        BigDecimal overLong=new BigDecimal("10003521543763483263500288202012");
        if(overLong.compareTo(new BigDecimal(Long.MAX_VALUE))>0){
            System.out.println("over LongNum: "+overLong);
        }

        if(new BigDecimal("546703633534681088").compareTo(new BigDecimal(Long.MAX_VALUE))>0){
            System.out.println("over LongNum: 546703633534681088");
        }else {
            System.out.println("normal LongNum: 546703633534681088");
        }
    }

    @Test
    public void printMinu(){
        for (int i = 0; i < 60; i++) {
            System.out.print(String.format("'%02d'",i)+",");
        }
    }

    @Test
    public void my(){
        Properties properties=new Properties();
        properties.setProperty("algorithmClassName","com.nolouser.demo.config.MyShardingAlgorithm");
        Optional<ShardingAlgorithm> serviceInstance = Optional.empty();
        for (ShardingAlgorithm each : ShardingSphereServiceLoader.newServiceInstances(ShardingAlgorithm.class)) {
            System.out.println(each.getType());
            if (each.getType().equalsIgnoreCase("CLASS_BASED")) {
                serviceInstance = Optional.of(each);
                break;
            }
        }

        System.out.println(serviceInstance);

        /*ShardingSphereAlgorithm algorithm = TypedSPIRegistry.getRegisteredService(ShardingAlgorithm.class, "CLASS_BASED", properties);
        System.out.println(algorithm);*/
    }

}
