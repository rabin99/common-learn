package com.sparkSQL

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object ParquetPartitionDiscovery {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("parquet discovery")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

//    val userDF =  sqlContext.read.parquet("hdfs://flu05:8020/wj/users/gender=male/country=US/users.parquet")
    val userDF =  sqlContext.read.parquet("hdfs://flu05:8020/wj/users/")
    userDF.printSchema()
    userDF.show()
  }
}
