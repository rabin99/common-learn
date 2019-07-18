package com.lin.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author linjh
 * @date 2019/1/12 19:08
 */
public class RebalanceCommitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RebalanceCommitConsumer.class);
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(loadProp());
        consumer.subscribe(Collections.singletonList("test-c"), new ConsumerRebalanceListener() {
            @Override
            /**
             * rebalance之前和consumer停止读取消息之后被调用，在此提交偏移量，下一个接管分区的消费者就知道从哪里开始读取了
             */
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                LOGGER.info("Lost partitinos in rebalance. Committing current offsets: " + currentOffsets);
                // 如果发生rebalance，需要提交的是处理过的分区对应的偏移量，所以这里需要使用currentOffsets
                consumer.commitSync(currentOffsets);
            }

            @Override
            /**
             * rebalance之后和消费者开始读取消息之前被调用
             */
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            }
        });

        try {
            consumer.poll(0);
            LOGGER.info("分配的消费者总数：{}", consumer.assignment().size());
            for (TopicPartition topicPartition : consumer.assignment()) {
                LOGGER.info("分配给了:{}", topicPartition.partition());
                consumer.seek(new TopicPartition("test-c", topicPartition.partition()), 2);
            }
//            consumer.seekToBeginning(Arrays.asList(new TopicPartition("test-c", 0), new TopicPartition("test-c", 1), new TopicPartition("test-c", 2)));
//            consumer.seekToEnd(Arrays.asList(new TopicPartition("test-c", 0), new TopicPartition("test-c", 1), new TopicPartition("test-c", 2)));


//            consumer.seek(new TopicPartition("test-c", 1), 3);
//            consumer.seek(new TopicPartition("test-c", 2), 3);
            for (; ; ) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                int num = 0;
                for (ConsumerRecord<String, String> record : records) {
                    // record.timestamp()消息发送到kafka的时间
                    LOGGER.info("record.partition()={} , record.offset()={} , record.value()={} ", record.partition(), record.offset(), record.value());
                    // //封装对应topic中对应partition的offset
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                    // 提交偏移量
                    consumer.commitAsync(currentOffsets, null);
                }
            }
        } catch (WakeupException e) {
            // 忽略异常，正在关闭消费者
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
        } finally {
            try {
                consumer.commitSync(currentOffsets);
            } finally {
                consumer.close();
                LOGGER.info("Closed consumer and we are done");
            }
        }
    }

    private static Properties loadProp() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "goods-group");
//        properties.put("auto.offset.reset", "earliest");
        // 非自动提交offset
        properties.put("enable.auto.commit", "false");
        return properties;
    }
}
