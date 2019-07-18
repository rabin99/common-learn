package datastream.eventtime;

import Utils.KafkaProps;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

/**
 * @author linjh
 * @date 2019/2/16 15:43
 */
public class EventTimeDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStreamSource<MyEvent> myEvent = env.addSource(new FlinkKafkaConsumer011<MyEvent>("flink-1", new MyDeserializationSchema(), KafkaProps.loadProp()));

        SingleOutputStreamOperator<MyEvent> max = myEvent.keyBy("id")
                .timeWindow(Time.seconds(5))
                .max("eventTime");
        max.print();
        env.execute("Event Time");
    }
}
