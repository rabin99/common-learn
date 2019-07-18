package com.sparkSQL

import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object GenericLoadSaveScala {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local")
      .setAppName("ManuallySpecifyOptions")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

//    val userDf = sqlContext.read.parquet("hdfs://flu05:8020/kylin/users.parquet")
    val userDf = sqlContext.read.json("hdfs://flu05:8020/kylin/people.json")
    userDf.show()

    userDf.select("name").write.format("parquet").mode(SaveMode.Append)save("hdfs://flu05:8020/wj/p_parquet")

    userDf.write.json("hdfs://flu05:8020/wj/p_json")


  }
}
