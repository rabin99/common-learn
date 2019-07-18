package com.basics;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/09
 */
public class BroadcastVariable {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("BroadcastVariable").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        final int factor = 3;
        Broadcast<Integer> broadcast = sparkContext.broadcast(factor);
        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> numbers = sparkContext.parallelize(numbersList);
        System.out.println(numbers.getNumPartitions());
        JavaRDD<Integer> multipleNumbers = numbers.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {

                Integer value = broadcast.getValue();
                return v1 * value;
            }
        });
        multipleNumbers.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        sparkContext.stop();
    }
}
