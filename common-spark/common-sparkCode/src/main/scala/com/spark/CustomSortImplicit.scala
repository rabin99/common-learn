package com.spark

import com.spark.CustomSort.Girl
import org.apache.spark.{SparkConf, SparkContext}
import org.spark_project.guava.collect.ComparisonChain

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/27
  */
object OrderContext {
  implicit val boyToOrdering = new Ordering[Boy] {
    override def compare(x: Boy, y: Boy): Int = {
      ComparisonChain.start().compare(x.faceValue, y.faceValue).compare(x.age, y.age).result()
    }
  }
}
object CustomSortImplicit {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CustomSort").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(("ljh", 90, 28, 1), ("ljh2", 90, 27, 2), ("ljh3", 95, 22, 3)))
    import OrderContext.boyToOrdering
    val rdd2 = rdd1.sortBy(x => Boy(x._2, x._3), false)
    println(rdd2.collect().toBuffer)
  }
}

case class Boy(val faceValue: Int, val age: Int) extends Serializable
