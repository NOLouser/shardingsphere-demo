package com.nolouser.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nolouser.demo.entity.TOrder;
import com.nolouser.demo.entity.TOrderExtend;
import com.nolouser.demo.mapper.TOrderExtendMapper;
import com.nolouser.demo.mapper.TOrderMapper;
import com.nolouser.demo.service.TOrderService;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nolouser
 * @since 2020-12-22
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TOrderServiceImpl.class);

    @Resource
    private TOrderExtendMapper tOrderExtendMapper;

    @Transactional
    public Object hello(){
        try (HintManager hintManager=HintManager.getInstance();){
            // 指定查询的分表
            hintManager.addTableShardingValue("t_order_hint","40");
            TOrderExtend tOrder=tOrderExtendMapper.selectOne(Wrappers.<TOrderExtend>lambdaQuery().eq(TOrderExtend::getUserId,9));
            LOGGER.info(tOrder.toString());
        }

        try (HintManager hintManager=HintManager.getInstance();){
            // 指定查询的分表
            hintManager.addTableShardingValue("t_order_hint","39");
            TOrderExtend tOrder=tOrderExtendMapper.selectOne(Wrappers.<TOrderExtend>lambdaQuery().eq(TOrderExtend::getUserId,9));
            LOGGER.info(tOrder.toString());
        }

        Map<String,String> result=new HashMap<>();
        result.put("word","hello, world!");

        return result;
    }

}
