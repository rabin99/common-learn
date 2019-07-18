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
object UDAF {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("UDAF")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 构造模拟数据
    val names = Array("Leo", "Marry", "Jack", "Tom", "Tom", "Tom", "Leo")
    val namesRDD = sc.parallelize(names, 5)

    val namesRowRDD = namesRDD.map(Row(_))
    val structType = StructType(Array(StructField("name", StringType, true)))

    val namesDF = sqlContext.createDataFrame(namesRowRDD,structType)
    namesDF.createOrReplaceTempView("names")

    sqlContext.udf.register("strCount",StringCount)

    sqlContext.sql("select name,strCount(name) from names group by name").collect().foreach(println)



  }
}
