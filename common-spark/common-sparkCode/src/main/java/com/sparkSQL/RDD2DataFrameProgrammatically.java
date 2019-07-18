package com.sparkSQL;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.tools.DataUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/23
 */
public class RDD2DataFrameProgrammatically {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("rdd to df pro");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sparkContext);
        JavaRDD<String> lines = sparkContext.textFile(DataUtil.getPath("students.txt"));

        JavaRDD<Row> map = lines.map(new Function<String, Row>() {
            @Override
            public Row call(String line) throws Exception {
                String[] split = line.split(",");
                return RowFactory.create(split[0],split[1],split[2]);
            }
        });

        List<StructField> structFields = new ArrayList<>();
        structFields.add(DataTypes.createStructField("id", DataTypes.StringType,true));
        structFields.add(DataTypes.createStructField("name", DataTypes.StringType,true));
        structFields.add(DataTypes.createStructField("age", DataTypes.StringType,true));
        StructType structType = DataTypes.createStructType(structFields);

        Dataset<Row> dataFrame = sqlContext.createDataFrame(map, structType);
        sqlContext.registerDataFrameAsTable(dataFrame,"student");
        Dataset<Row> resultDs = sqlContext.sql("select name from student group by name");

        List<Row> collect = resultDs.javaRDD().collect();
        collect.forEach(ele -> System.out.println(ele));

    }
}
