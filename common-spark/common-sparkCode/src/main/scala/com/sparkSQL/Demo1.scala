package com.sparkSQL

import org.apache.log4j.Level
import org.apache.log4j.lf5.LogLevel
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.internal.util.TableDef.Column

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/22
  */
object Demo1 extends App {
  val conf = new SparkConf().setMaster("local").setAppName("sparksql 1")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val sqlContext = new SQLContext(sc)
  val df = sqlContext.read.json("E:\\bilin-learn\\learnspark\\新建文件夹\\第73讲-Spark SQL：DataFrame的使用\\文档\\students.json")
  df.show()
  df.printSchema()
  df.select("name").show()
  df.select("age","id").limit(2).show()

  df.select(df.col("name"),(df.col("age")+1).as("ljh")).show(2)

  df.filter(df("age")>21).show()
  df.select("name").distinct().show()

  sc.stop()

}
