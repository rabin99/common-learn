package com.spark

import org.apache.spark.Partitioner

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/27
  */
class URLCountPartition(ins: Array[String]) extends Partitioner {
  val parMap = new scala.collection.mutable.HashMap[String, Int]()
  var count = 0
  for (i <- ins) {
    parMap += (i -> count)
    count += 1
  }

  override def numPartitions: Int = ins.length

  override def getPartition(key: Any): Int = {
    parMap.getOrElse(key.toString, 0)
  }
}
