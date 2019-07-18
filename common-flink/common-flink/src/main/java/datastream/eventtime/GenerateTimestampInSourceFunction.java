package datastream.eventtime;

import Utils.KafkaProps;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;

/**
 * @author linjh
 * @date 2019/2/16 15:43
 */
public class GenerateTimestampInSourceFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // 在添加数据源处直接生成Timestamps和Watermarks
        DataStreamSource<MyEvent> dataStreamSource = env.addSource(new SourceFunction<MyEvent>() {

            @Override
            public void run(SourceContext<MyEvent> ctx) throws Exception {

                KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaProps.loadProp());
                consumer.subscribe(Collections.singleton("flink-1"));
                try {
                    for (; ; ) {
                        ConsumerRecords<String, String> records = consumer.poll(100);
                        // 处理kafka数据逻辑
                        records.forEach(record->{
                            String[] split = record.value().split(",");
                            MyEvent myEvent = new MyEvent(split[0], Long.parseLong(split[1]), split[2]);
                            //生成timestamp
                            ctx.collectWithTimestamp(myEvent,myEvent.getEventTime());
                            // 生成watermarks ，实际中应该是隔一段时间生成水印，而不是生成时间戳就生成一个水印
                            ctx.emitWatermark(new Watermark(myEvent.getEventTime()));

                        });
                        consumer.commitAsync();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        consumer.commitSync();
                    }finally {
                        consumer.close();
                    }
                }
            }
            @Override
            public void cancel() {
                // nothing
            }
        });

        SingleOutputStreamOperator<MyEvent> max = dataStreamSource.keyBy("id")
                .timeWindow(Time.seconds(5))
                .max("eventTime");
        max.print();
        env.execute("Event Time");
    }
}
