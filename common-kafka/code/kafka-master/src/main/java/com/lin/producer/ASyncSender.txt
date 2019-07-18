package com.lin.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.stream.IntStream;

/**
 * 异步发送
 *
 * @author linjh
 * @date 2019/1/10 14:21
 */
public class ASyncSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(ASyncSender.class);

    public static void main(String[] args) {
        Properties properties = initPros();
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        IntStream.range(0, 10).forEach(i -> {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test_c", String.valueOf(i), "hello " + i);

            // 异步回调方法
            producer.send(record, (recordMetadata, execption) -> {
                        // 发送成功
                        if (execption == null) {
                            LOGGER.info("key is {},offset {},partition {}", i, recordMetadata.offset(),recordMetadata.partition());
                        }
                    }
            );
            producer.flush();
//            producer.close();
        });
    }

    private static Properties initPros() {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
