package com.sparkRdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/28
  */
object AccumulatorDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("accumulator")
    val sc = new SparkContext(conf)
    sc.setLogLevel("error")
//    var accu = sc.accumulator(0,"error accumulator")
    var accu = sc.doubleAccumulator

    val rdd = sc.parallelize(1 to 10)
    val mapRDD = rdd.map(x => {
      if (x % 2 == 0) {
        accu.add(1)
       0
      }
      1
    })

    mapRDD.count()
    mapRDD.count()
    println("第一次 打印："+accu.value)
    mapRDD.foreach(println)
    println("第二次 打印："+accu.value+accu.name.get)
    sc.stop()
  }

}
