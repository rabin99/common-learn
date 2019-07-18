package com.sparkSQL

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object DailyUV {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("DailyUV")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    import sqlContext.implicits._
    val userAccessLog = Array("2015-10-01,1122", "2015-10-01,1122", "2015-10-01,1123", "2015-10-01,1124", "2015-10-01,1124", "2015-10-02,1122", "2015-10-02,1121", "2015-10-02,1123", "2015-10-02,1123")
    val userAccessLogRDD = sc.parallelize(userAccessLog, 5)
    val userAccessLogRowRDD = userAccessLogRDD
      .map { log => Row(log.split(",")(0), log.split(",")(1).toInt) }
    // 构造DataFrame的元数据
    val structType = StructType(Array(
      StructField("date", StringType, true),
      StructField("userId", IntegerType, true)))
    // 使用SQLContext创建DataFrame
    val userAccessLogRowDF = sqlContext.createDataFrame(userAccessLogRowRDD, structType)

    userAccessLogRowDF.groupBy('date).agg(year(to_date('date))).collect().foreach(println)

  }
}
