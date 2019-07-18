package datastream.eventtime;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;

import javax.annotation.Nullable;

/**
 * @author linjh
 * @date 2019/2/16 15:43
 */
public class GenerateTimestampByAssigner {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        ExecutionConfig config = env.getConfig();
        // 生成水印间隔
        config.setAutoWatermarkInterval(5000);

        String filePath = "D:\\scala-project\\learn-flink\\src\\main\\java\\datastream\\eventtime\\GenerateTimestampByAssigner.java";
        DataStreamSource<String> stream = env.readFile(new TextInputFormat(new Path(filePath)), filePath, FileProcessingMode.PROCESS_CONTINUOUSLY, 100, BasicTypeInfo.STRING_TYPE_INFO);

        SingleOutputStreamOperator<MyEvent> withTimestampsAndWatermarks = stream.map(new MapFunction<String, MyEvent>() {
            @Override
            public MyEvent map(String record) throws Exception {
                String[] split = record.split(",");
                return new MyEvent(split[0], Long.parseLong(split[1]), split[2]);
            }
        }).assignTimestampsAndWatermarks(new AssignerWithPunctuatedWatermarks<MyEvent>() {
            @Nullable
            @Override
            public Watermark checkAndGetNextWatermark(MyEvent lastElement, long extractedTimestamp) {
                // lastElement 是否含有watermark
                return (lastElement.getEventTime() < System.currentTimeMillis()-5000) ? new Watermark(extractedTimestamp) : null;
            }

            @Override
            public long extractTimestamp(MyEvent element, long previousElementTimestamp) {
                return element.getEventTime();
            }
        });

        withTimestampsAndWatermarks.keyBy("eventTime").timeWindow(Time.seconds(10)).sum(2).print();

        env.execute("Event Time");
    }

}

class BoundedOutOfOrdernessGenerator implements AssignerWithPeriodicWatermarks<MyEvent> {

    private final long maxOutOfOrderness = 3500;
    private long currentMaxTimestamp;

    /*
    每次调用分配器的方法，如果返回的水印非空并且大于先前的水印，则将发出新的水印
     */
    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
    }

    @Override
    public long extractTimestamp(MyEvent element, long previousElementTimestamp) {
        long timestamp = element.getEventTime();
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
        return timestamp;
    }
}

/**
 * 通常最好保持接收到的最大时间戳，并创建具有最大预期延迟的水印，而不是从当前系统时间减去。
 * BoundedOutOfOrdernessGenerator相对更常用些
 */
class TimeLagWatermarkGenerator implements AssignerWithPeriodicWatermarks<MyEvent> {
    private final long maxTimeLag = 5000;

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(System.currentTimeMillis() - maxTimeLag);
    }

    @Override
    public long extractTimestamp(MyEvent element, long previousElementTimestamp) {
        return element.getEventTime();
    }
}

class MyAssignerWithPunctuatedWatermarks implements AssignerWithPunctuatedWatermarks<MyEvent> {
    @Nullable
    @Override
    public Watermark checkAndGetNextWatermark(MyEvent lastElement, long extractedTimestamp) {
        return (lastElement.getEventTime() < System.currentTimeMillis() - 5000) ?
                new Watermark(extractedTimestamp) : null;
    }

    @Override
    public long extractTimestamp(MyEvent element, long previousElementTimestamp) {
        return element.getEventTime();
    }
}