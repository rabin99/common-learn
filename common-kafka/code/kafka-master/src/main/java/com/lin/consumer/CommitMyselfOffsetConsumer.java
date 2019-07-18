package com.lin.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author linjh
 * @date 2019/1/12 19:08
 */
public class CommitMyselfOffsetConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommitMyselfOffsetConsumer.class);
    private static int count = 0;
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(loadProp());
        consumer.subscribe(Collections.singletonList("test-c"));

        try {
            for (; ; ) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                int num = 0;
                for (ConsumerRecord<String, String> record : records) {
                    // record.timestamp()消息发送到kafka的时间
                    LOGGER.info("record.partition()={} , record.value()={} ", record.partition(), record.value());
                    Thread.sleep(5000);
                    // //封装对应topic中对应partition的offset
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));

                    LOGGER.info("提交之前：{} ", record.offset());
                    if (count % 2 == 0) {
                        // 假设这里返回的批次数据很多，需要处理很久，那么可以分批提交，而不用等到处理完毕再提交
                        consumer.commitAsync(currentOffsets, null);

                        LOGGER.info("提交之后：{} \n", record.offset());
                    }
                    count++;
                    num++;
                }
                LOGGER.info("一个批次大小：{}" + num);
                consumer.commitAsync();
            }
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    private static Properties loadProp() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "goods-group");
        properties.put("auto.offset.reset", "earliest");
        // 非自动提交offset
        properties.put("enable.auto.commit", "false");
        return properties;
    }
}
