package datastream.eventtime;

import Utils.KafkaProps;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

/**
 * @author linjh
 * @date 2019/2/19 16:22
 */
public class GenerateTimestampKafkaConsumer {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        FlinkKafkaConsumer011<MyEvent> kafkaSource = new FlinkKafkaConsumer011<MyEvent>("flink-1",new MyDeserializationSchema(), KafkaProps.loadProp());

        // 具有递增时间戳的分发者 ,水印直接是递增的时间戳
        kafkaSource.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<MyEvent>(){
            @Override
            public long extractAscendingTimestamp(MyEvent element) {
                return element.getEventTime();
            }
        });

        // 允许固定数量的迟到的分配者
        kafkaSource.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<MyEvent>(Time.seconds(5)) {
            @Override
            public long extractTimestamp(MyEvent element) {
                return element.getEventTime();
            }
        });
        DataStreamSource<MyEvent> stream = env.addSource(kafkaSource);
    }
}
