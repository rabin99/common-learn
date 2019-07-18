package com.sparkSQL

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object HiveDatasource {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("hive datasource")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)

    hiveContext.sql("select * from zzl").show()
  }
}
