package com.sparkSQL;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/23
 */
public class JDBCDataSource {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local")
                .setAppName("JDBCDataSource");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        Map<String,String> options = new HashMap<>();
        options.put("url", "jdbc:mysql://localhost:3306/blog");
        options.put("dbtable", "test");
        Dataset<Row> studentInfosDF = sqlContext.read().format("jdbc").options(options).load();
        options.put("dbtable", "student_scores");
        Dataset<Row> studentScoresDF = sqlContext.read().format("jdbc")
                .options(options).load();
       studentInfosDF.javaRDD().mapToPair(new PairFunction<Row, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Row row) throws Exception {
                return null;
            }
        });
       // ....不想写了


    }
}
