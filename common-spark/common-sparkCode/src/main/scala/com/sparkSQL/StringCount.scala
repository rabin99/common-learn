package com.sparkSQL

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, StructField, StructType}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object StringCount extends UserDefinedAggregateFunction {
  // inputSchema输入数据类型
  override def inputSchema: StructType = {
    StructType(Array(StructField("count", IntegerType, true)))
  }

  // 聚合过程所处理的数据类型
  override def bufferSchema: StructType = {
    StructType(Array(StructField("count", IntegerType, true)))
  }

  // 返回值类型
  override def dataType: DataType = {
    IntegerType
  }

  //这个函数是确定性的，也就是说，给定相同的输入，总是返回相同的输出。
  override def deterministic: Boolean = {
    true
  }

  // 为每个分组的数据执行初始化操作
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0
  }

  // 指的是，每个分组，有新的值进来的时候，如何进行分组对应的聚合值的计算
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getAs[Int](0) + 1
  }

  //  // 由于Spark是分布式的，所以一个分组的数据，可能会在不同的节点上进行局部聚合，就是update
  // 但是，最后一个分组，在各个节点上的聚合值，要进行merge，也就是合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getAs[Int](0) +buffer2.getAs[Int](0)
  }

  // // 最后，指的是，一个分组的聚合值，如何通过中间的缓存聚合值，最后返回一个最终的聚合值
  override def evaluate(buffer: Row): Any = {
    buffer.getAs[Int](0)
  }
}
