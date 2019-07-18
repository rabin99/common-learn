package com.sparkSQL

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object ParquetLoadData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local")
      .setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val usersDF = sqlContext.read.parquet("hdfs://flu05:8020/kylin/users.parquet")
    usersDF.createOrReplaceTempView("user")
    val userNameDF = sqlContext.sql("select name from user")
    userNameDF.rdd.map(row => row(0)).foreach(println)
  }
}
