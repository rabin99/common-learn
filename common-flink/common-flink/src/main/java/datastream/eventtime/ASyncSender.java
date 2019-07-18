package datastream.eventtime;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;

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
        Random random = new Random();
        IntStream.range(0, 100).forEach(i -> {
//            String value = String.format("%s %s %s", random.nextInt(10),System.currentTimeMillis(),"info-"+i);
            String value = String.format("%s %s %s", i,System.currentTimeMillis(),"info-"+i);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("flink-1", String.valueOf(i), value);
            System.out.println(value);
            /*producer.send(record, (recordMetadata, execption) -> {
                        // 发送成功
                        if (execption == null) {
                            LOGGER.info("key is {},offset {},partition {}", i, recordMetadata.offset(),recordMetadata.partition());
                        }
                    }*/
//            );
            producer.flush();
            try {
//                System.out.printf("spout: %d , value: %s....,\n",i,value);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
