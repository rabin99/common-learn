package com.lin.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author linjh
 * @date 2019/1/11 11:48
 */
public class MyPartitioner implements Partitioner {

    private final String LOGOIN = "LOGOIN";
    private final String LOGOFF = "LOGOFF";
    private final String ORDER = "ORDER";

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        System.out.println("partition....");
        if (keyBytes == null || keyBytes.length == 0) {
            throw new IllegalArgumentException("The key is required for BIZ");
        }
        switch (key.toString().toUpperCase()) {
            case LOGOIN:
                return 0;
            case LOGOFF:
                return 1;
            case ORDER:
                return 2;
            default:
                throw new IllegalArgumentException("The key is required for BIZ");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {
        // 在partition之前全局运行一次，可以完成一些获取配置文件的作用
        System.out.println("configure......");
    }
}