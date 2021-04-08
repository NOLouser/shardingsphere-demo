package com.nolouser.demo.note;

import com.nolouser.demo.note.design.decorator.Cake;
import com.nolouser.demo.note.design.decorator.CandleDecorator;
import com.nolouser.demo.note.design.decorator.FruitDecorator;
import org.junit.jupiter.api.Test;


public class CommonTest {

    @Test
    public void retryException() throws Exception {
        Integer retry=3;
        retry++;
        while (retry-- >0){
            try {
                System.out.println(retry);
                throw new Exception("达到最大重试次数，抛出");
            }catch (Exception e){
                if (retry==2)
                    throw e;
            }
        }
    }

    /**
     * 装饰者模式示例
     */
    @Test
    public void decorator(){
        Cake cake = new Cake();
        System.out.println(cake.getDescription() + "总共花费" + cake.cost());

        FruitDecorator fruitDecorator = new FruitDecorator(cake);
        System.out.println(fruitDecorator.getDescription() + "总共花费" + fruitDecorator.cost());

        CandleDecorator candleDecorator = new CandleDecorator(fruitDecorator);
        System.out.println(candleDecorator.getDescription() + "总共花费" + candleDecorator.cost());
    }


}
