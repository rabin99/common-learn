package datastream;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author linjh
 * @date 2019/2/16 12:59
 */
public class MyIterationStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        DataStreamSource<Long> someIntegers = env.generateSequence(0, 10);
        IterativeStream<Long> iteration = someIntegers.iterate();

        DataStream<Long> minusOne = iteration.map(vlaue -> vlaue - 1);
        DataStream<Long> stillGreaterThanZero = minusOne.filter(value -> value > 0);

        //关闭 param ：feedbackStream，
        iteration.closeWith(stillGreaterThanZero);
        DataStream<Long> lessThenZero = minusOne.filter(value -> value <= 0);

        //closeWith相当于迭代器中每个元素都会执行一次map方法，得到minusOne，
        // 然后对比是否满足停止条件，如果有元素还没满足，则没满足的元素继续执行map方法
        //第一个次减1，得到-1，0，1，2，那么还有元素不满足，
        // 则不满足的1，2拿出来执行第二次得到：0，1，还有元素不满足，
        //不满足的1拿出来执行，第三次得到：0，所有最终结果是：-1，0，1，2，0，1，0
//        minusOne.print();
        // 2,1,1
//        stillGreaterThanZero.print();
        // 0 ,-1 ,0,0
//        lessThenZero.print();
        // 0 ,1,2,3
//        someIntegers.print();
        env.execute("my iteration stream");
    }
}
