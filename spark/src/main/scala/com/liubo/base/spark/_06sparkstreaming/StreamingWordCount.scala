package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingWordCount {
  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("StreamingWordCount")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    //创建StreamingContext对象，与集群进行交互
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(10))
    //如果目录中有新创建的文件，则读取
    val lines = ssc.textFileStream("E:\\data\\streaming")
    //分割为单词
    val words = lines.flatMap(_.split(" "))
    //构造Map(x,1)集合，统计单词出现次数
    val wordscount = words.map(x=>(x,1)).reduceByKey(_+_)

    wordscount.print()
    //启动SparkStreaming
    ssc.start()
    //一直运行
    ssc.awaitTermination()
  }
}
