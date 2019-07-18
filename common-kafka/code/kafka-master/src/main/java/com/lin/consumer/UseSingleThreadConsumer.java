package com.lin.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @author linjh
 * @date 2019/1/18 11:39
 */
public class UseSingleThreadConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,ConsumerConfig.KEY_DESERIALIZER_CLASS_DOC);
    }
}
