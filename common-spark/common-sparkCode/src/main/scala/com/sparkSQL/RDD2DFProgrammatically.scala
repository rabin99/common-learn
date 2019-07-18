package com.sparkSQL

import com.tools.DataUtil
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object RDD2DFProgrammatically {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("RDD2DataFrameProgrammatically")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val studentDF = sc.textFile(DataUtil.getPath("students.txt")).map(line => {
      val arr = line.split(",")
      Row(arr(0),arr(1),arr(2))
    })
    val schemaString = "id name age"
    val fields = schemaString.split(" ").map(fieldName => StructField(fieldName,StringType,true))
    val schema = StructType(fields)
    val df = sqlContext.createDataFrame(studentDF,schema)
    df.createOrReplaceTempView("student")
    val result = sqlContext.sql("select * from student where age <18")
    result.rdd.collect().foreach(println)

  }
}
