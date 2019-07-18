package com.sparkSQL

import com.tools.DataUtil
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description:
  *
  * @author linjh
  * @date 2018/10/23
  */
object RDD2DFReflection extends App {

  val conf = new SparkConf().setMaster("local").setAppName("rdd to df")
  val sc = new SparkContext(conf)

  val sqlContext = new SQLContext(sc)

  import sqlContext.implicits._

  case class Student(id: String, name: String, age: String)

  val studentDF = sc.textFile(DataUtil.getPath("students.txt")).map(line => {
    val arr = line.split(",")
    new Student(arr(0), arr(1), arr(2))
  }).toDF()
//  studentDF.registerTempTable("student")
  studentDF.createOrReplaceTempView("student")
  val df = sqlContext.sql("select * from student")
  val resultRDD = df.rdd
  resultRDD.map(row => {
    Student(row(0).toString, row(1).toString, row(2).toString)
  }).collect().foreach(println(_))


  resultRDD.map(row => {
    val map = row.getValuesMap(Array("name","age"))
    (map("name"),map("age"))
  }).collect().foreach(println(_))
}
