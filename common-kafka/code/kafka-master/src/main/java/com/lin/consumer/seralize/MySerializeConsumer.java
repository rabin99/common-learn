package com.lin.consumer.seralize;

import com.lin.producer.serialize.Customer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

/**
 * @author linjh
 * @date 2019/1/13 16:25
 */
public class MySerializeConsumer {
    private final static Logger LOG = LoggerFactory.getLogger(MySerializeConsumer.class);
    public static Properties init() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.lin.consumer.seralize.CustomerDeserializer");
        properties.put("group.id", "goods-group");
        properties.put("auto.offset.reset", "earliest");
        return properties;
    }

    public static void main(String[] args) {
        KafkaConsumer<String,Customer> consumer = new KafkaConsumer<String, Customer>(init());
        consumer.subscribe(Collections.singletonList("my-s"));

        try {
            for (; ; ) {
                ConsumerRecords<String, Customer> records = consumer.poll(100);
                for (ConsumerRecord<String, Customer> record : records) {
                    LOG.info("record.partition()={} ,record.offset()={} ,record.value()={}", record.partition(), record.offset(), record.value().toString());
                }
                consumer.commitAsync();
            }
        }catch (WakeupException e){
            //ignore shutdown
        }catch (Exception e){
            LOG.error("Unexpected error",e);
        }finally {
            try {
                consumer.commitSync();
            }catch (Exception e){
                consumer.close();
            }
        }
    }
}
