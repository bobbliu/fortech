package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*
*reduceByWindow
* reduceByWindow方法基于滑动窗口对源DStream中的元素进行聚合操作，返回包含单元素的一个新的DStream。
* */

object WindowWordCount03 {
  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("WindowWordCount03")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(1))
    ssc.checkpoint(".")
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_ONLY_SER)
    val words = lines.flatMap(_.split(" "))
    //val reduceByWindow = words.map(x => 1).reduceByWindow(_ + _, _ - _, Seconds(5), Seconds(1))

    //以过去5秒钟为一个输入窗口，每1秒统计一下WordCount，本方法会将过去5秒钟的每一秒钟的WordCount都进行统计
    //然后进行叠加，得出这个窗口中的单词统计。 这种方式被称为叠加方式
    //val wordCounts = words.map(x => (x, 1)).reduceByKeyAndWindow(_ + _,Seconds (5), Seconds(1))

    //高效的方式：
    //计算t+4秒这个时刻过去5秒窗口的WordCount，可以将t+3时刻过去5秒的统计量加上[t+3，t+4]的统计量
    //再减去[t-2，t-1]的统计量，这种方法可以复用中间三秒的统计量，提高统计的效率。 这种方式被称为增量方式，
    val wordCounts = words.map(x => (x, 1)).reduceByKeyAndWindow(_ + _, _ - _, Seconds(5), Seconds(1))
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
