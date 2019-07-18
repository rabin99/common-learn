package com.lin.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * @author linjh
 * @date 2019/1/12 19:08
 */
public class ExitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExitConsumer.class);
    private static KafkaConsumer<String, String> consumer;

    public static void main(String[] args) {
        consumer = new KafkaConsumer<String, String>(loadProp());
        consumer.subscribe(Collections.singletonList("test-d"));

        final Thread mainThread = Thread.currentThread();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("Starting exit....");
                consumer.wakeup();
            }
        }.start();

        try {
            consumer.poll(0);
            for (TopicPartition topicPartition : consumer.assignment()) {
                consumer.seek(topicPartition, 1);
            }

            for (; ; ) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                records.forEach(record -> {
                    LOGGER.info("record.key()={}, record.offset()={}, record.partition()={}, record.timestamp()={}, " +
                            "record.value()={} ", record.key(), record.offset(), record.partition(), record.timestamp(), record.value());
                });
                consumer.commitAsync();
            }
        } catch (WakeupException e) {
            // ignore for shutdown
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
                LOGGER.info("consumer close");
            }
        }
    }

    private static Properties loadProp() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test_group");
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", "false");
        return properties;
    }
}
