package com.sparkSQL;

import com.tools.DataUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/23
 */

public class JsonDataSource {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName("rdd to dataframe");

        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sparkContext);
        JavaRDD<String> lines = sparkContext.textFile(DataUtil.getPath("students.txt"));
        JavaRDD<Row> map = lines.map(new Function<String, Row>() {
            @Override
            public Row call(String line) throws Exception {
                String[] split = line.split(" ");
                return RowFactory.create(split[0], split[1], split[2]);
            }
        });
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("id", DataTypes.StringType,true));
        fields.add(DataTypes.createStructField("name", DataTypes.StringType,true));
        fields.add(DataTypes.createStructField("age", DataTypes.StringType,true));
        StructType studentType = DataTypes.createStructType(fields);
        Dataset<Row> dataFrame = sqlContext.createDataFrame(map, studentType);
        dataFrame.createOrReplaceGlobalTempView("student");
        Dataset<Row> sql = sqlContext.sql("select * from student age > 10");
        sql.show();

    }
}
