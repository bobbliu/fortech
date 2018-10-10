package com.liubo.base.spark._09sparkpersisit

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Spark共享变量之累加器Accumulator（excutor只能累加，不能读取）
  *
  * 需要注意的是，累加器的执行必须需要Action触发
  */

object SparkAccumulatorOps {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("SparkAccumulatorOps")
      .getOrCreate()
    val sc = sparkSession.sparkContext

    // 要对这些变量都*7，同时统计能够被3整除的数字的个数
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
    val listRDD: RDD[Int] = sc.parallelize(list)
    var counter = 0
    val counterAcc = sc.accumulator[Int](0)
    val mapRDD = listRDD.map(num => {
      counter += 1
      println(s"excutor counter===$counter")
      if (num % 3 == 0) {
        counterAcc.add(1)
      }
      num * 7
    })
    mapRDD.foreach(println)
    println(s"driver 变量 counter===$counter")
    println(s"累计器 counterAcc===$counterAcc")
    sc.stop()
  }
}
