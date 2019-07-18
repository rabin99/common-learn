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
public class RebalanceSeekConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RebalanceSeekConsumer.class);
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(loadProp());
        consumer.subscribe(Collections.singletonList("test-c"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                // consumer停止消费之后，提交offset到数据库
                commitDbTransaction();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                for (TopicPartition partition : partitions) {
                    // 获取偏移量
                    consumer.seek(partition, getOffsetFromDB(partition));
                }
            }
        });
// 第一次poll，让消费者加入到group中，获取到相应分区
// 因为后面会seek重置偏移量，所以这里即使poll也不影响数据消费
        consumer.poll(0);
        for (TopicPartition topicPartition : consumer.assignment()) {
            consumer.seek(topicPartition, getOffsetFromDB(topicPartition));
        }
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                processRecored(record);
                storeRecordInDB(record);
                // 这里假定保存数据库非常快，分则，可以采用指定提交偏移量方法，来优化
                storeOffsetInDb(record.topic(), record.partition(), record.offset());
            }
            commitDbTransaction();
        }

    }

    private static void storeOffsetInDb(String topic, int partition, long offset) {
    }

    private static void storeRecordInDB(ConsumerRecord<String, String> record) {
    }

    private static void processRecored(ConsumerRecord<String, String> record) {
    }

    private static long getOffsetFromDB(TopicPartition partition) {
        return 0;
    }

    private static void commitDbTransaction() {
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
