package com.lin.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author linjh
 * @date 2019/1/18 14:28
 */
public class MyProduerInterceptor implements ProducerInterceptor<String, String> {
    /**
     * ProducerConfig.INTERCEPTOR_CLASSES_CONFIG
     * interceptor.class来配置
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 对消息的处理,这里全部转为大写
        return new ProducerRecord<String, String>(record.topic(), record.partition(), record.timestamp(), record.key()
                , record.value().toUpperCase());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // 收到应道处理
        if(exception == null) {
            System.out.println("应答！！！");
        }else {
            exception.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
