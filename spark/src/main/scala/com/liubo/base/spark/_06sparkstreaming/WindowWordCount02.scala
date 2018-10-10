package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.api.java.StorageLevels
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*
* countByWindow
* */
object WindowWordCount02 {
  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("WindowWordCount02")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(5))
    ssc.checkpoint(".")
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_ONLY_SER)
    val words = lines.flatMap(_.split(" "))
    //countByWindow方法计算基于滑动窗口的DStream中的元素的数量。
    val wordsCount = words.map(x => (x, 1)).countByWindow(Seconds(60), Seconds(15))
    wordsCount.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
