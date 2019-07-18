package com.lin.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 普通发送，发送后不关注是否成功
 *
 * @author linjh
 * @date 2019/1/10 14:21
 */
public class PartitionerSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionerSender.class);
    private static String[] bizType = {"LOGOIN", "LOGOFF", "ORDER"};

    public static void main(String[] args) {
        Properties properties = initPros();
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        IntStream.range(0, 10).forEach(i -> {
            String key = bizType[new Random().nextInt(3)];
            String value = "hello " + i;
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("fire_and_forget_sender", key, value);
            producer.send(record, (metadata, exception) -> {
                LOGGER.info("key : {},value : {}, partition : {}", key, value  , metadata.partition());
            });
        });
        producer.flush();
    }

    private static Properties initPros() {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("partitioner.class", "com.lin.producer.MyPartitioner");
        return properties;
    }
}

