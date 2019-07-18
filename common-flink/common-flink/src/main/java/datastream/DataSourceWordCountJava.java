package datastream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.IntegerTypeInfo;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author linjh
 * @date 2019/2/15 17:08
 */


public class DataSourceWordCountJava {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum =
                env.readFile(new TextInputFormat(new Path("D:\\scala-project\\learn-flink\\src\\main\\java\\datastream\\DataSourceWordCountJava.java")),"D:\\scala-project\\learn-flink\\src\\main\\java\\datastream\\DataSourceWordCountJava.java"
                , FileProcessingMode.PROCESS_CONTINUOUSLY,5000, BasicTypeInfo.STRING_TYPE_INFO)
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                        for (String word : value.split(" ")) {
                            out.collect(new Tuple2<String, Integer>(word, 1));
                        }
                    }
                }).keyBy(0)

                .sum(1);
        sum.print();
        try {
            env.execute("java window count");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}