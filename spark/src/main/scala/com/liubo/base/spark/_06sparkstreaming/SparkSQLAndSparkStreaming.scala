package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}

object SparkSQLAndSparkStreaming {
  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("SparkSQLAndSparkStreaming")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(2))
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_ONLY_SER)
    val words = lines.flatMap(_.split(" "))

    //调用foreachRDD方法，遍历DStream中的RDD
    words.foreachRDD((rdd: RDD[String], time: Time) => {
      val sqlContext = SQLContextSingleton.getInstance(rdd.sparkContext)

      import sqlContext.implicits._

      val wordsDataFrame = rdd.map(w => Record(w)).toDF()
      wordsDataFrame.createOrReplaceTempView("words")
      val wordsCountDataFrame = sqlContext.sql("SELECT  word, count(1) as total FROM words GROUP BY word")
      println(s"=======$time=======")
      wordsCountDataFrame.show()

    })
    ssc.start()
    ssc.awaitTermination()
  }
  case class Record(word: String)
}
