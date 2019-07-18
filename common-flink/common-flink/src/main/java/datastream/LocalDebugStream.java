package datastream;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.DataStreamUtils;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Iterator;

/**
 * @author linjh
 * @date 2019/2/16 12:59
 */
public class LocalDebugStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        DataStreamSource<Integer> dss = env.fromElements(1, 2, 3, 4, 5, 6, 7, 8, 9);
        SingleOutputStreamOperator<String> map = dss.map(new MapFunction<Integer, String>() {
            @Override
            public String map(Integer value) throws Exception {
                return value + "aaaa";
            }
        });
        Iterator<String> collect = DataStreamUtils.collect(map);
        collect.forEachRemaining(value -> System.out.println(value));
//        env.execute("LocalDebugStream");
    }
}
