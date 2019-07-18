package datastream;

import Utils.KafkaProps;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @author linjh
 * @date 2019/2/15 17:08
 */


public class MyCustomerDataSourceWordCountJava {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<Tuple2<String, Integer>> aaa = env.addSource(new FlinkKafkaConsumer011<String>("aaa", new SimpleStringSchema(), KafkaProps.loadProp()))
                .setParallelism(2)
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                        for (String word : value.split(" ")) {
                            out.collect(new Tuple2<>(word, 1));
                        }
                    }
                }).keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);
//        aaa.addSink(new MySink());
        aaa.writeAsText("D:\\scala-project\\learn-flink\\src\\main\\resources\\test");
        try {
            env.execute("java window count");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class MySink extends RichSinkFunction<Tuple2<String,Integer>> {
    @Override
    public void invoke(Tuple2<String, Integer> value, Context context) throws Exception {
        System.out.printf("value.f0 : %s , value.f1 : %d \n",value.f0,value.f1);
    }
}