package com.lin.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author linjh
 * @date 2019/1/12 19:08
 */
public class NoGroupConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoGroupConsumer.class);

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(loadProp());
//        consumer.subscribe(Collections.singletonList("test_c"));

        // 如果topic新增了partition，不会主动通知，而需要用户周期调用partitionsFor
        List<PartitionInfo> partitionInfoList = consumer.partitionsFor("aaa");

        //构建一个topic和partitino的对象的列表
        List<TopicPartition> topicPartition = new ArrayList<>();
        if (partitionInfoList != null) {
            for (PartitionInfo partition : partitionInfoList) {
                topicPartition.add(new TopicPartition(partition.topic(), partition.partition()));
            }
        }
        // 给消费者分配指定partition，这样就不需要使用group.id
        consumer.assign(topicPartition);

        try {
            for (; ; ) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                records.forEach(record -> {
                    // record.timestamp()消息发送到kafka的时间
                    LOGGER.info("record.key()={}, record.offset()={}, record.partition()={}, " +
                            "record.value()={} ", record.key(), record.offset(), record.partition(), record.value());
                });
                // 这里先直接异步提交，速度更快，这里即使失败了，下一次提交很可能会成功。
                consumer.commitAsync();
            }
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
        } finally {
            try {
                // 如果上面失败了，直接关闭consumer，那么就没有所谓的下一次提交了，这里使用同步，肯定会在这里一直重试
                consumer.commitSync();
            } finally {
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
//        properties.put("auto.offset.reset", "earliest");
        return properties;
    }
}
