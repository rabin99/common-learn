package com.streaming

import org.apache.spark.api.java.StorageLevels
import org.apache.spark.streaming.{Durations, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/24
  */
object WordCountScala {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Word Count Scala")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Durations.seconds(5))

    val resourceDStream = ssc.socketTextStream("localhost", 9999, StorageLevels.MEMORY_ONLY)
    val wordCount = resourceDStream.filter(!_.isEmpty).flatMap(_.split(" ")).map((_, 1))
    wordCount.foreachRDD(rdd => {
      rdd.collect().toList.toString()
    })

  }

}
