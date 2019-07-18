package com.lin.producer.serialize;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author linjh
 * @date 2019/1/14 11:48
 */
public class AvroSender {
    private final static Logger LOG = LoggerFactory.getLogger(MySerializeSender.class);

    public static Properties init() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "flu02:9093,flu03:9093,flu04:9093");

        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        properties.put("schema.registry.url", "http://flu03:8081");
        return properties;
    }

    public static void main(String[] args) {
        String schemaString = "{\n" +
                "  \"namespace\": \"customerManagerment.avro\",\n" +
                "  \"type\": \"record\",\n" +
                "  \"name\": \"Customer\",\n" +
                "  \"fields\":[\n" +
                "    {\"name\":\"id\",\"type\":\"int\"},\n" +
                "    {\"name\":\"name\",\"type\":\"string\"}\n" +
                "  ]\n" +
                "}";

        KafkaProducer<Integer, GenericRecord> producer = new KafkaProducer<>(init());
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(schemaString);

        // 序列化器会将schema提取，并保存到注册表中，并用它序列化对象
        GenericRecord customer = new GenericData.Record(schema);
        customer.put("id", 1);
        customer.put("name", "lin");
        ProducerRecord<Integer, GenericRecord> data = new ProducerRecord<>("customer-schema", 1, customer);
        producer.send(data);
        producer.flush();
        producer.close();


    }
}
