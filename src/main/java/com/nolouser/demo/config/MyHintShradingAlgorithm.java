package com.nolouser.demo.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @description 强制路由算法
 * @author wanghp
 * @date 2020/12/31 15:03
 */
public class MyHintShradingAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedList<>();
        for (String availableTargetName : availableTargetNames) {
            for (String value : shardingValue.getValues()) {
                if (availableTargetName.endsWith(value)){
                    result.add(availableTargetName);
                }
            }
        }

        return result;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "CLASS_BASED_HINT";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties props) {

    }
}
