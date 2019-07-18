package com.sparkRdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/09
  */
object SecondSort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("second sort").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("C:\\test\\sort.txt")
    val pairs = lines.filter(line => line.split(" ").length ==2).map(line => {
        (new SecondSortKey(line.split(" ")(0).toInt, line.split(" ")(1).toInt), line)
    })
    val sortedPairs = pairs.sortByKey()
    val sortLines = sortedPairs.map(sortedPairs => sortedPairs._2)
    //    sortLines.foreach(println)
    println(sortLines.top(100).toList.toString())
  }

}
