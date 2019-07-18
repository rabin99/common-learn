package com.sparkRdd

import org.apache.spark.util.AccumulatorV2

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/28
  */
class MyAccumulator extends AccumulatorV2[Double,Double]{
  private var result = 0;

  override def isZero: Boolean = ???

  override def copy(): AccumulatorV2[Double, Double] = ???

  override def reset(): Unit = ???

  override def add(v: Double): Unit = ???

  override def merge(other: AccumulatorV2[Double, Double]): Unit = ???

  override def value: Double = ???
}
