package com.sparkRdd

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/09
  */
object BroadcastVariable {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Broadcast variable scala").setMaster("local")
    val sc = new SparkContext(conf)
    val b: Broadcast[Int] = sc.broadcast(3)
    val numbers = sc.parallelize(List(1, 2, 3, 4, 5))
    val rdd = numbers.map(x => b.value * x)
    rdd.foreach(println(_))
  }
}
