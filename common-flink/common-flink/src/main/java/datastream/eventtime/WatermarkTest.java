package datastream.eventtime;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.DataStreamUtils;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author linjh
 * @date 2019/2/19 17:53
 */
public class WatermarkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStreamSource<String> input = env.socketTextStream("flu03", 12019);
        DataStream<MyEvent> inputMap = input.filter(line -> {
            return line.split("\\s").length == 3;
        }).map(new MapFunction<String, MyEvent>() {
            @Override
            public MyEvent map(String value) throws Exception {
                String[] split = value.split("\\s");
                return new MyEvent(split[0], Long.parseLong(split[1]), split[2]);
            }
        });

/*
        DataStream<MyEvent> watermark = inputMap.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<MyEvent>() {
            private long currentMaxTimestamp = 0;

            // 最大允许的乱序时间是10s
            private final long maxOutOfOrderness = 5000L;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            private Watermark watermark;

            @Nullable
            @Override
            public Watermark getCurrentWatermark() {
                watermark = new Watermark(currentMaxTimestamp - maxOutOfOrderness);
                return watermark;
            }

            @Override
            public long extractTimestamp(MyEvent element, long previousElementTimestamp) {
                long timestamp = element.getEventTime();
                currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
                System.out.printf("Watermark ：event : %s; timestamp : %s, %s; currentMaxTimestamp : %s, %s, watermark : %s \n", element.getId()+" | "+format.format(element.getEventTime())+" | "+element.getInfo(),
                        timestamp, format.format(timestamp), currentMaxTimestamp, format.format(currentMaxTimestamp), watermark.toString()+" - "+format.format(watermark.getTimestamp()));
                return timestamp;
            }
        });
        // 会话窗口window
        // 时间窗口
        SingleOutputStreamOperator<String> result = watermark.keyBy("id")
              .timeWindow(Time.seconds(10),Time.seconds(5))
              .apply(new WindowFunction<MyEvent, String, Tuple, TimeWindow>() {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

                    @Override
                    public void apply(Tuple tuple, TimeWindow window, Iterable<MyEvent> input, Collector<String> out) throws Exception {
                        input.forEach(event -> {
                            out.collect(String.format("event.Id():%s, event.EventTime():%s, event.Info():%s,  watermark : %s - %s", event.getId(), format.format(event.getEventTime())
                                    , event.getInfo(), format.format(window.getStart()), format.format(window.getEnd())));
                        });


                    }
                });*/

        // 计数窗口：这里因为前有keyBy，所以这里count的是对相同id有5个才输出
   /*     SingleOutputStreamOperator<String> result = inputMap.keyBy("id")
                .countWindow(5)
                //IN, OUT, KEY, W extends Window
                .apply(new WindowFunction<MyEvent, String, Tuple, GlobalWindow>() {
                    @Override
                    public void apply(Tuple tuple, GlobalWindow window, Iterable<MyEvent> input, Collector<String> out) throws Exception {
                        input.forEach(event->{
                            out.collect(tuple.toString() + "-" + tuple.getField(0) + ":" + event.toString() +":"+ window.maxTimestamp());
                        });

                    }
                });*/

        // 这里直接统计个数
        SingleOutputStreamOperator<String> result = inputMap
                .countWindowAll(5)
                .apply(new AllWindowFunction<MyEvent, String, GlobalWindow>() {
                    @Override
                    public void apply(GlobalWindow window, Iterable<MyEvent> values, Collector<String> out) throws Exception {
                        values.forEach(event->{
                            out.collect( event.toString() +":"+ window.maxTimestamp());
                        });
                    }
                });
        Iterator<String> collect = DataStreamUtils.collect(result);
        collect.forEachRemaining(s -> System.out.println("分组：" + s));
        env.execute("watermark !!!");
    }
}
