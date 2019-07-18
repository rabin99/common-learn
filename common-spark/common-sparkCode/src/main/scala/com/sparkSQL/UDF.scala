package com.sparkSQL

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object UDF {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("UDF")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val names = Array("Leo", "Marry", "Jack", "Tom")
    val namesRDD = sc.parallelize(names, 5)
    val namesRowRDD = namesRDD.map(name => Row(name))
    val structType = StructType(Array(StructField("name", StringType, true)))
    val namsDF = sqlContext.createDataFrame(namesRowRDD, structType)
    namsDF.createOrReplaceTempView("names")

    sqlContext.udf.register("strLen", (str: String) => str.length)
    sqlContext.sql("select name,strLen(name) from names").collect().foreach(println)

  }
}
