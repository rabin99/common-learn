package com.lin.producer.serialize;

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
 * @author linjh
 * @date 2019/1/13 16:25
 */
public class MySerializeSender {
    private final static Logger LOG = LoggerFactory.getLogger(MySerializeSender.class);
    public static Properties init() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.lin.producer.serialize.CustomerSerializer");
        return properties;
    }

    public static void main(String[] args) {
        KafkaProducer<String, Customer> producer = new KafkaProducer<String, Customer>(init());
        IntStream.range(10, 15).forEach((int i) -> {


            ProducerRecord<String, Customer> recod = new ProducerRecord<>("my-s","mys-key" + i, new Customer(i, "lin" + i));
            try {
                RecordMetadata metadata = producer.send(recod).get();
                LOG.info("key={}, value={}, metadata.partition()={}, metadata.offset()={}, metadata.toString()={}","mys-key" + i,new Customer(i, "lin"+i),metadata.partition(),metadata.offset(),metadata.toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });
    }
}
