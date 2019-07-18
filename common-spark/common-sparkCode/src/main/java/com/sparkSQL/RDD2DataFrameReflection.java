package com.sparkSQL;

import com.tools.DataUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

/**
 * Description: RDD转DataFrame
 *
 * @author linjh
 * @date 2018/10/22
 */
public class RDD2DataFrameReflection {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName("rdd to dataframe");

        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sparkContext);
        JavaRDD<String> lines = sparkContext.textFile(DataUtil.getPath("students.txt"));

        JavaRDD<Student> studentJavaRDD = lines.map(new Function<String, Student>() {
            @Override
            public Student call(String line) throws Exception {
                String[] context = line.split(",");
                return new Student(context[0], context[1], Integer.valueOf(context[2]));
            }
        });

        Dataset<Row> dataFrame = sqlContext.createDataFrame(studentJavaRDD, Student.class);
        dataFrame.registerTempTable("students");
        Dataset<Row> dateSet = sqlContext.sql("select * from students where age >20 ");

        JavaRDD<Row> rowJavaRDD = dateSet.javaRDD();
        JavaRDD<com.sparkSQL.Student> studentRDD = rowJavaRDD.map(new Function<Row, Student>() {
            @Override
            public Student call(Row row) throws Exception {
                return new Student(row.getString(1), row.getString(2), row.getInt(0));
            }
        });


        studentRDD.foreach(new VoidFunction<com.sparkSQL.Student>() {
            @Override
            public void call(Student student) throws Exception {
                System.out.println(student.toString());
            }
        });
        sparkContext.stop();
    }
}
