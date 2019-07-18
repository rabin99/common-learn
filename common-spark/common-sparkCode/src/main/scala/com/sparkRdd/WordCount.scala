package com.sparkRdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/11
  */
object WordCount extends App {

  val conf = new SparkConf().setAppName("second sort").setMaster("local[*]")
  val sc = new SparkContext(conf)
  //创建hadoopRDD-> MapPartitionsRDD
  val lines = sc.textFile("C:\\test\\sort1.txt")
  // MapPartitionsRDD
  lines.flatMap(_.split(" "))
    //MapPartitionsRDD
    .map((_,1))
    //   ShuffledRDD
    .reduceByKey(_+_)
    // mapPartitions
    .foreach(println)

}
