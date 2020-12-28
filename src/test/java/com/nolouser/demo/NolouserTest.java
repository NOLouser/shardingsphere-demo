package com.nolouser.demo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NolouserTest {

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

}
