package com.sparkRdd

import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.ClassTag

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/26
  */
object RddDemo {
  def main(array: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("RDD Demo")
    val sc = new SparkContext(conf)
    sc.setLogLevel("error")

    /*
    /*
    join
     */
    val rdd1 = sc.parallelize(List(("tom", 1), ("jerry", 2), ("kitty", 3)))
    val rdd2 = sc.parallelize(List(("jerry", 9), ("tom", 8), ("shuke", 7)))

    val joinRDD = rdd1.join(rdd2).collect()
    joinRDD.foreach(println(_))
    joinRDD.foreach(x => {
      val value = x._2
      println(value._2)
    })

    /**
      * leftOuterJoin,rightOuterJoin
      */
    val leftOuterJoinRDD = rdd1.leftOuterJoin(rdd2)
    leftOuterJoinRDD.foreach(println)

    val rightOuterJoinRDD = rdd1.rightOuterJoin(rdd2)
    rightOuterJoinRDD.foreach(println)

    /**
      * union
      */
    val unionRDD = rdd1 union rdd2
    println("union ..")
    unionRDD.foreach(println(_))

    /**
      * groupByKey
      */
    val groupByKeyRDD = unionRDD.groupByKey()
    println("groupbykey..")
    groupByKeyRDD.foreach(println)

    val groupByRDD = unionRDD.groupBy(x => x._1)
    groupByRDD.foreach(println)

    val arr = sc.textFile("hdfs://dc1:8020/user/test/wc/helloworld.txt").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect()
    arr.foreach(println(_))

    val rdd3 = sc.parallelize(List(("tom", 1), ("tom", 2), ("jerry", 3), ("kitty", 2)))
    val rdd4 = sc.parallelize(List(("jerry", 2), ("tom", 1), ("shuke", 2)))
    println("===========cogroup==============")
    val cogroupRDD = rdd3.cogroup(rdd4)
    cogroupRDD.foreach(println)
    println("===========join==============")
    val joinRDD2 = rdd3.join(rdd4).collect()
    joinRDD2.foreach(println(_))
    println(joinRDD2.count(x => x._1.equals("tom")))
    println(joinRDD2.count(x => true))
    println("===========cartesian==============")
    val cartesianRDD = rdd3.cartesian(rdd4)
    cartesianRDD.foreach(println)
    println("===========mapValues==============")
    val mapValuesRDD = sc.parallelize(List(("a", 1), ("b", 2), ("c", 3)))
    val value = mapValuesRDD.mapValues(x => {
      if (x > 2) {
        "shabi!"
      }
    })
    value.foreach(println)
    println("===========mapPartitions==============")
    val bigRdd = sc.parallelize(1 to 100)
    val m1 = bigRdd.mapPartitionsWithIndex((index, it) => {
      //      val list = it.toList
      //      Iterator(index +list.toString())
      Iterator(index + ":" + it.toList.toString())
    })
    m1.foreach(x => println("current partition：" + x))
    println("...............")
    val mapPartitionsRdd = bigRdd.mapPartitions(partition => {
      var total = 0
      partition.foreach(elem => {
        total += elem
      })
      Iterator(total)
    })
    mapPartitionsRdd.foreach(println)
    println("使用sum：" + mapPartitionsRdd.sum())
    println("user reduce:" + mapPartitionsRdd.reduce(_ + _))


    val bigRdd2 = sc.parallelize(List(1 to 5, 10 to 15))
    val mapPartitionsRdd2 = bigRdd.mapPartitions(partition => {
      val arr = partition.toList
      val count = arr.sum
      Iterator(count)
    })


    mapPartitionsRdd2.foreach(println)
    println("使用sum：" + mapPartitionsRdd2.sum())
    println("user reduce:" + mapPartitionsRdd2.reduce(_ + _))

    println("===========aggregate  ==============")
    val rdd5 = sc.parallelize((1 to 5))
    val aggregateResult = rdd5.aggregate("")(_ + _, _ + _)
    println(aggregateResult)


    println("===========aggregate比较特俗的例子：min比较==============")
    val rdd6 = sc.parallelize(List("12", "23", "345", ""), 2)
    rdd6.mapPartitionsWithIndex((index, iterator) => {
      Iterator(index + "==" + iterator.toList.toString())
    }).foreach(println)
    val aggResult = rdd6.aggregate("")((x, y) => math.min(x.length, y.length).toString, (x, y) => x + y)
    println(aggResult)


    val rdd7 = sc.parallelize(List(1, 2, 3, 4))
    val rdd7Tesult = rdd7.aggregate(0, 0)((x, y) => (x._1 + y, x._2 + 1), (x, y) => (x._1 + y._1, x._2 + y._2))
    println(rdd7Tesult._1 / rdd7Tesult._2.toFloat)

    println("===========aggregateByKey==============")
    val pairRDD = sc.parallelize(List(("cat", 2), ("cat", 5), ("mouse", 4), ("cat", 12), ("dog", 12), ("mouse", 2)), 2)
    pprintln(pairRDD)

    val abk = pairRDD.aggregateByKey(0)((x, y) => x + y, _ + _)

    sc.setCheckpointDir("hdfs://dc1:8020/user/ck/")
    abk.checkpoint()
    //    abk.cache()
    println(abk.collect().toList.toString())
    println("===========checkpoint==============")
    println("是否checkpointd：" + abk.isCheckpointed)
    println("===========coalesce, repartition==============")
    val rdd8 = sc.parallelize(1 to 1000, 10)
    println("当前rdd8分区个数：" + rdd8.getNumPartitions)
    //    rdd8.repartition(30)
    //    println("当前rdd8 repartition分区个数：" + rdd8.getNumPartitions)
    val rdd9 = rdd8.coalesce(5)
    println("当前rdd9 coalesce分区个数：" + rdd9.getNumPartitions)

    println("===========zip ==============")
    val rdd1 = sc.parallelize(1 to 1000, 10)
    val zipRDD = rdd1.zip(rdd8)
    println("zipRDD分区个数：" + zipRDD.getNumPartitions)
    println("===========collectAsMap==============")
    val map = zipRDD.collectAsMap()
    //    println(map.toMap.toString())

    println("===========combineByKey ==============")

    type MyType = (Int, Int)
    val avg = rdd10.combineByKey(score => (score, 1), (c1: MyType, newScore) => (c1._2 + newScore, c1._2 + 1), (c1: MyType, c2: MyType) => (c1._1 + c2._1, c1._2 + c2._2))
    println(avg.collect().toList.toString())
    val result = avg.aggregate(0, 0)((x, y) => (x._1 + y._2._1, x._2 + y._2._2), (x, y) => (x._1 + y._1, x._2 + y._2))

    println(result)
*/
    println("===========countByKey /countByValue ==============")
    val rdd10 = sc.parallelize(List(("a", 1), ("b", 2), ("b", 2), ("c", 2), ("c", 1),("c",1)))
    println("" + rdd10.countByKey().toString())
    println("" + rdd10.countByValue().toString())

    println("===========filterByRange ==============")

    println(rdd10.filterByRange("a","b").collect().toList.toString)

    println("===========flatMapValues ==============")
    val str = rdd10.flatMapValues(x => {
      Iterator(x+2)
    }).collect().toList.toString()
    println(str)

    val flatRDD = sc.parallelize(List(("a","a b c d "),("b","q w e r   "),("c","z x c v")))
    println(flatRDD.flatMapValues(x => {
      x.split(" ")
    }).collect().toList.toString())

    println(flatRDD.mapValues(_.split(" ")).collect().toList.toString())

    println("===========foldByKey ==============")
    val rdd1 = sc.parallelize(List("dog","dog", "wolf", "cat", "bear"), 2)
    val rdd2 = rdd1.map(x=>(x,x.length))
    val rddFold  = rdd2.foldByKey(0)((x,y) => x +y)
    println(rddFold.collect().toList.toString())

    println("===========foreachPartition常用（action） ==============")

    val rddPartition = sc.parallelize(1 to 100,10)
    rddPartition.foreachPartition(it => {
//      while (it.hasNext){
//        print(it.next())
//      }
      for(s <- it){
        print(s)
      }
      println("......")
    }
    )
    println("===========keyBy ==============")
    val rddKey = sc.parallelize(List(("a", 1), ("b", 2), ("b", 2), ("c", 2), ("c", 1),("c",1)))
    println(rddKey.keyBy(f => {
      f._1+f._2
    }).collect().toList.toString())
    println("===========keys/values ==============")
    println(rddKey.keys.collect().toList.toString())
    println(rddKey.values.collect().toList.toString())

    sc.stop()
  }

  def pprintln[T: ClassTag](rdd: org.apache.spark.rdd.RDD[T]): Unit = {
    rdd.mapPartitionsWithIndex((index, iterator) => {
      Iterator(index + "==" + iterator.toList.toString())
    }).foreach(println)
  }

}
