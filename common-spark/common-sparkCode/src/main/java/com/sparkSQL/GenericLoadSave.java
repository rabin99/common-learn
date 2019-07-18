package com.sparkSQL;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/23
 */
public class GenericLoadSave {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[2]")
                .setAppName("GenericLoadSave");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        Dataset<Row> usersDF = sqlContext.read().load("hdfs://flu05:8020/kylin/users.parquet");
//        Dataset<Row> select = usersDF.select("name", "favorite_color");
//        select.show();
//
//
//        select.write().save("hdfs://flu05:8020/wj/word.parquet");

        System.out.println(usersDF.schema());
        usersDF.printSchema();
        try {
            usersDF.registerTempTable("user");
            Dataset<Row> sql = sqlContext.sql("select * from user");
            sql.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
