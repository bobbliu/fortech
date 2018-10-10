package com.liubo.base.spark._09sparkpersisit

import org.apache.spark.sql.SparkSession

/**
  * Spark RDD的持久化
  */

object SparkPersisitOps {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("SparkPersisitOps")
      .getOrCreate()
    val sc = sparkSession.sparkContext
    var start = System.currentTimeMillis()
    val linesRDD = sc.textFile("E:\\Data\\wordcount.txt")
    linesRDD.cache()
    //    linesRDD.persist(StorageLevel.MEMORY_ONLY_SER)

    //执行第一次RDD计算
    val retRDD = linesRDD.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)
    retRDD.cache()
    //    retRDD.persist(StorageLevel.MEMORY_ONLY_SER)
    retRDD.count()
    println(s"第一次计算耗时：${System.currentTimeMillis() - start}ms")

    //执行第二次RDD计算
    start = System.currentTimeMillis()
    linesRDD.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _).count()
    retRDD.count()
    println(s"第二次计算耗时：${System.currentTimeMillis() - start}ms")

    linesRDD.unpersist()
    sc.stop()
  }
}
