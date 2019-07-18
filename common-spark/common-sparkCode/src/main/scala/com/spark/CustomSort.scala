package com.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.spark_project.guava.collect.ComparisonChain

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/27
  */
object CustomSort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CustomSort").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(("ljh", 90, 28, 1), ("ljh2", 90, 27, 2), ("ljh3", 95, 22, 3)))
    val rdd2 = rdd1.sortBy(x => Girl(x._2,x._3),false)
    println(rdd2.collect().toBuffer)
    sc.stop()
  }

  case class Girl(val faceValue: Int, val age: Int) extends Ordered[Girl] with Serializable {
    override def compare(that: Girl): Int = {
      ComparisonChain.start().compare(this.faceValue, that.faceValue).compare(this.age, that.age).result()
    }
  }

}
