package com.sparkRdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/09
  */
object TopTake {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("top take").setMaster("local[*]")
    conf.set("spark.ui.enabled","false")
    conf.set("spark.deploy.spreadOut","false")
    val sc = new SparkContext(conf)

    val line = sc.textFile("C:\\test\\sort1.txt")
    val keys = line.map(x => {
      x.split(" ")(1).toInt
    })
    println(keys.take(10).toList.toString)
    println(keys.top(10).toList.toString)

  }

}
