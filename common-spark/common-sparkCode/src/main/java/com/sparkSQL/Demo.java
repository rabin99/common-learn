package com.sparkSQL;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

import java.util.Properties;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/22
 */
public class Demo {
    public static void main(String[] args) throws Exception{
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName("demo1");

        SQLContext sqlContext = new SQLContext(new JavaSparkContext(conf));
        String path = Demo.class.getClassLoader().getResource("students.json").getPath();
        System.out.println("路径为：" + path);
        Dataset ds = sqlContext.read().json(path);
        ds.select("age").show();
        System.out.println(ds.filter(ds.col("age").gt("21")).select("name","age").limit(1));
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","root");
//        Class.forName("com.mysql.jdbc.Driver");
//        ds.filter(ds.col("age").gt("21")).select("age","name").write().mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/blog","test",properties);
        ds.select("age","name").write().mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/blog","test",properties);


    }
}
