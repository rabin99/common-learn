package com.lin.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * 同步发送
 *
 * @author linjh
 * @date 2019/1/10 14:21
 */
public class SyncSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncSender.class);

    public static void main(String[] args) {
        Properties properties = initPros();
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        IntStream.range(0, 10).forEach(i -> {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test-d", String.valueOf(i), "goods " + i);
            Future<RecordMetadata> future = producer.send(record);
            try {
                RecordMetadata recordMetadata = future.get();
//                Thread.sleep(1000);
                LOGGER.info("key is {},offset {}", i, recordMetadata.offset());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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
