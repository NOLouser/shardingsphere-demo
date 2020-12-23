package com.nolouser.demo.config;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

public class MyShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    // 时间戳在snowflake生成的key的二进制位数是2~42(从左到右)
    // 参考: https://shardingsphere.apache.org/document/current/cn/features/sharding/concept/key-generator/
    private static final long timestampLeftShift;
    private static final DateTimeFormatter dtForm;
    public static final long EPOCH;

    static {
        timestampLeftShift = 22L;
        dtForm = DateTimeFormatter.ofPattern("mm");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String tableExt;
        // snowflake生成的新键
        // 反解析出timestamp
        long snowflakeKey = shardingValue.getValue();
        String snowflakeBit = Long.toBinaryString(snowflakeKey);
        int bitLen = snowflakeBit.length();
        int timeStart = (int) (bitLen < timestampLeftShift ? 0 : bitLen - timestampLeftShift);
        String timestampStr = timeStart == 0 ? "0" : snowflakeBit.substring(0, timeStart);
        long timestamp = Long.parseLong(timestampStr, 2) + EPOCH;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        tableExt = localDateTime.format(dtForm);

        for (String availableTableName : availableTargetNames) {
            if (availableTableName.endsWith(tableExt)) {
                return availableTableName;
            }
        }
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "CLASS_BASED";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties props) {
    }
}
