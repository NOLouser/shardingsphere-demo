package com.nolouser.demo.service;

import com.nolouser.demo.entity.TOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class TOrderServiceTest {

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private DataSource dataSource;

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
        TOrder tOrder = tOrderService.getById(548438946523623424L);
        System.out.println(tOrder);
    }




}
