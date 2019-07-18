package com.lin.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.stream.IntStream;

/**
 * 普通发送
 *
 * @author linjh
 * @date 2019/1/10 14:21
 */
public class AcksSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcksSender.class);

    public static void main(String[] args) {
        Properties properties = initPros();
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        long start = System.currentTimeMillis();
        IntStream.range(0, 10000).forEach(i -> {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("fire_and_forget_sender", String.valueOf(i), "hello " + i);
            producer.send(record);
            LOGGER.info("The message is send done and the key is {}", i);
            producer.flush();
//            producer.close();
        });
        System.out.println("时间消耗" + (System.currentTimeMillis() - start));
    }

    private static Properties initPros() {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("acks", "all"); // 0-时间消耗3727   1-时间消耗15663  时间消耗31319
        return properties;
    }
}
