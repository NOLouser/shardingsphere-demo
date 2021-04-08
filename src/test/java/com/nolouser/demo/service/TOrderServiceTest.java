package com.nolouser.demo.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nolouser.demo.entity.TOrder;
import com.nolouser.demo.entity.TOrderExtend;
import com.nolouser.demo.mapper.TOrderExtendMapper;
import com.nolouser.demo.mapper.TOrderMapper;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SpringBootTest
public class TOrderServiceTest {

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private DataSource dataSource;

    @Resource
    private TOrderExtendMapper tOrderExtendMapper;

    @Resource
    private TOrderMapper tOrderMapper;

    /**
     * 创建60个分表
     */
    @Test
    public void createTables(){
        String sql = "CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, address_id BIGINT NOT NULL, status VARCHAR(50), datetime VARCHAR(50), PRIMARY KEY (order_id))";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void insert(){
        for (int i = 0; i < 10; i++) {
            TOrder order = new TOrder();
            order.setUserId(i);
            order.setAddressId((long)i);
            order.setStatus("INSERT_TEST");
            order.setDatetime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            System.out.println(tOrderService.save(order));
        }
    }

    @Test
    public void queryById(){
        TOrder tOrder = tOrderService.getById(551424380258201601L);
        System.out.println(tOrder);
    }

    @Test
    public void getObj(){
        Object object = tOrderService.getObj(Wrappers.<TOrder>query()
                .select("order_id as temp_order_id","user_id as temp_user_id")
                .eq("order_id", 551424380258201601L), new Function<Object, Object>() {
            @Override
            public Object apply(Object o) {
                return o;
            }
        });
        System.out.println(object);
    }

    /**
     * 强制路由
     */
    @Test
    public void queryWithHint(){
        try (HintManager hintManager=HintManager.getInstance();
        ){
            // 指定查询的分表
            hintManager.addTableShardingValue("t_order_hint","40");
            TOrder tOrder=tOrderExtendMapper.selectOne(Wrappers.<TOrderExtend>lambdaQuery().eq(TOrderExtend::getUserId,9));
            System.out.println(tOrder);
        }

    }

    @Test
    public void batchInsert() {
        List<TOrder> tOrders = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            TOrder order = new TOrder();
            order.setUserId(i);
            order.setAddressId((long)i);
            order.setStatus("INSERT_TEST");
            order.setDatetime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

            tOrders.add(order);
        }

        int saveOk = tOrderMapper.insertBatchSomeColumn(tOrders);
        System.out.println(saveOk);
    }




}
