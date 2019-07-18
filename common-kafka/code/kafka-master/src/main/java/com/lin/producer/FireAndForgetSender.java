package com.lin.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.stream.IntStream;

/**
 * 普通发送，发送后不关注是否成功
 *
 * @author linjh
 * @date 2019/1/10 14:21
 */
public class FireAndForgetSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(FireAndForgetSender.class);

    public static void main(String[] args) {
        Properties properties = initPros();
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        IntStream.range(10, 2000).forEach(i -> {
//            ProducerRecord<String, String> record = new ProducerRecord<String, String>("fire_and_forget_sender", String.valueOf(i), "hello " + i);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("my-0118", String.valueOf(i), "hello " + i);
            producer.send(record);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("The message is send done and the key is {}", i);

//            producer.close();
        });

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        producer.flush();
    }

    private static Properties initPros() {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

//        properties.put("batch.size","1000000");
//        properties.put("linger.ms","5000");
        properties.put("client.id","linjh");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,"com.lin.producer.MyProduerInterceptor");
        return properties;
    }
}
