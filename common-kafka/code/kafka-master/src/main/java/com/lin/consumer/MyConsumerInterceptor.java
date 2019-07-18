package com.lin.consumer;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author linjh
 * @date 2019/1/18 14:40
 */
public class MyConsumerInterceptor implements ConsumerInterceptor<String,String> {
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        Map<TopicPartition, List<ConsumerRecord<String,String>>> results = new HashMap<>();
        Set<TopicPartition> partitions = records.partitions();
        partitions.forEach(p->
        {
            List<ConsumerRecord<String, String>> result = records.records(p).stream().filter(record->record.value().equals("HELLO 1")).collect(Collectors.toList());
            results.put(p,result);
        });
        return new ConsumerRecords<>(results);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

        // 如果是自动提交offset，那么这个方法会按照配置的时间间隔运行：
//        System.out.println("=========");
//        System.out.println(offsets);
//        System.out.println("=========");
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }
}
