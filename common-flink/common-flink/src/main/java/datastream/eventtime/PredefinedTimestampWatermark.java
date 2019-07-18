package datastream.eventtime;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author linjh
 * @date 2019/2/19 16:56
 */
public class PredefinedTimestampWatermark {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        DataStreamSource<String> dss = env.socketTextStream("flu03", 12019);

        SingleOutputStreamOperator<MyEvent> stream = dss.map(line -> {
            String[] split = line.split(",");
            return new MyEvent(split[0], Long.parseLong(split[1]), split[2]);
        });
        // 具有递增时间戳的分发者
        stream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<MyEvent>() {
            @Override
            public long extractAscendingTimestamp(MyEvent element) {
                return element.getEventTime();
            }
        });

        // 允许固定数量的迟到的分配者
        stream.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<MyEvent>(Time.seconds(10)) {
            @Override
            public long extractTimestamp(MyEvent element) {
                return element.getEventTime();
            }
        });
    }
}
