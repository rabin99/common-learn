package com.basics;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.util.LongAccumulator;
import org.spark_project.guava.collect.ImmutableList;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/09/27
 */
public class AccumulatorVariable {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Accumulator").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);


        SparkContext sc = sparkContext.sc();
        LongAccumulator sum = sc.longAccumulator();
//        Accumulator<Integer> sum = sparkContext.intAccumulator(0);
        ImmutableList<Integer> immutableList = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        JavaRDD<Integer> numbers = sparkContext.parallelize(immutableList);
//        numbers.foreach(new VoidFunction<Integer>() {
//            @Override
//            public void call(Integer integer) throws Exception {
//                sum.add(integer);
//            }
//        });
        JavaRDD newData = numbers.map(new Function<Integer, Object>() {
            @Override
            public Object call(Integer v1) throws Exception {
                if(v1%2==0){
                    sum.add(1);
                }
                return v1;
            }
        });
       /* newData.foreach(new VoidFunction() {
            @Override
            public void call(Object o) throws Exception {
                System.out.print(o);
            }
        });*/
//        newData.count();
        newData.count();
        System.out.println("第一次："+sum.value());

//        newData.foreach(new VoidFunction() {
//            @Override
//            public void call(Object o) throws Exception {
//                System.out.print(o);
//            }
//        });

        System.out.println("第二次："+sum.value());

        sparkContext.close();
    }


}
