package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*
* reduceByKeyAndWindow
* */
object WindowWordCount01 {
  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("WindowWordCount01")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(5))
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_ONLY_SER)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKeyAndWindow((a: Int, b: Int) => a + b, Seconds(60), Seconds(15))
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
