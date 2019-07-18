package com.sparkSQL

import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/23
  */
object ParquetMergeSchema {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local")
      .setAppName("ParquetMergeSchema")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    val studentsWithNameAge = Array(("leo", 23), ("jack", 25)).toSeq
    val studentsWithNameAgeDF = sc.parallelize(studentsWithNameAge, 2).toDF("name","age")
    studentsWithNameAgeDF.write.mode(SaveMode.Append).parquet("hdfs://flu05:8020/wj/student")

    val studentsWithNameGrade = Array(("leo", "AAAA"), ("tom", "BBBB")).toSeq
    val studentsWithNameGradeDF = sc.parallelize(studentsWithNameGrade, 2).toDF("name", "grade")
    studentsWithNameGradeDF.write.mode(SaveMode.Append).parquet("hdfs://flu05:8020/wj/student")
    val student = sqlContext.read.option("mergeSchema","true").parquet("hdfs://flu05:8020/wj/student")
    student.printSchema()
    student.createOrReplaceTempView("student")

    student.sqlContext.sql("select * from student").show()
  }
}
