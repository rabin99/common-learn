package com.lin.consumer.seralize;

import com.lin.producer.serialize.Customer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * @author linjh
 * @date 2019/1/15 16:12
 */
public class AvroConsumer {
    private final static Logger LOG = LoggerFactory.getLogger(AvroConsumer.class);
    public static Properties init() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        properties.put("group.id", "goods-group");
        properties.put("schema.registry.url","http://flu03:8081");
        return properties;
    }

    public static void main(String[] args) {
        KafkaConsumer<Integer,GenericRecord> consumer = new KafkaConsumer<>(init());
        consumer.subscribe(Collections.singletonList("customer-schema"));

        try {
            for (; ; ) {
                ConsumerRecords<Integer, GenericRecord> records = consumer.poll(100);
                for (ConsumerRecord<Integer, GenericRecord> record : records) {
                    // 这里接收还是得使用Avro提供的，好像可以强制转换，案例中没有报错
                    Customer value = (Customer) record.value();
                    LOG.info("record.partition()={} ,record.offset()={} ,record.value()={}", record.partition(), record.offset(), value.toString());
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
