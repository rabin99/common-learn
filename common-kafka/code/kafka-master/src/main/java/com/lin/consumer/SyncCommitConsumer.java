package com.lin.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * @author linjh
 * @date 2019/1/12 19:08
 */
public class SyncCommitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncCommitConsumer.class);

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(loadProp());
        consumer.subscribe(Collections.singletonList("test_c"));
        try {
            for (; ; ) {
                // 第一次调用poll会负责查找GroupCoordinator
                ConsumerRecords<String, String> records = consumer.poll(100);

                records.forEach(record -> {
                    // record.timestamp()消息发送到kafka的时间
                    LOGGER.info("record.key()={}, record.offset()={}, record.partition()={}, record.timestamp()={}, " +
                            "record.value()={} ", record.key(), record.offset(), record.partition(), record.timestamp(), record.value());
                });
                // 这里先直接异步提交，速度更快，这里即使失败了，下一次提交很可能会成功。
                consumer.commitAsync();
            }
        }catch (Exception e){
            LOGGER.error("Unexpected error",e);
        }finally {
            try {
                // 如果上面失败了，直接关闭consumer，那么就没有所谓的下一次提交了，这里使用同步，肯定会在这里一直重试
                consumer.commitSync();
            }finally {
                // 关闭立即出发rebalance。
                consumer.close();
            }
        }
    }

    private static Properties loadProp() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test_group-1");
        properties.put("auto.offset.reset", "earliest");
        // 非自动提交offset
        properties.put("enable.auto.commit", "false");
        return properties;
    }
}
