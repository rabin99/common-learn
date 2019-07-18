package com.sparkRdd

import org.apache.spark.Partitioner

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/09
  */
class MyPartitions extends Partitioner{
  override def numPartitions = ???

  override def getPartition(key: Any) = ???
}
