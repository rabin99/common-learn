package com.sparkSQL;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/23
 */
public class SaveModeTest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local")
                .setAppName("SaveModeTest");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        Dataset<Row> json = sqlContext.read().format("json").load("hdfs://flu05:8020/kylin/people.json");
        Dataset<Row> sample = json.sample(0.99);
        sample.show();
    }
}
