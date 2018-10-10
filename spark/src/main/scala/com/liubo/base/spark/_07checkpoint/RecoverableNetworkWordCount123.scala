package com.liubo.base.spark._07checkpoint

import java.io.File
import java.nio.charset.Charset

import com.google.common.io.Files
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}

object RecoverableNetworkWordCount123 {
  val ip = "localhost"
  val port = 9999
  val outputPath = "E:\\data\\demo\\streaming"
  val checkpointDirectory = "E:\\data\\demo\\checkpoint"
  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("RecoverableNetworkWordCount")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def createContext(ip: String, port: Int, outputPath: String, checkpointDirectory: String): StreamingContext = {
    //程序第一运行时会创建该条语句，如果应用程序失败，则会从checkpoint中恢复，该条语句不会执行
    println("Creating new context")
    val outputFile = new File(outputPath)
    if (outputFile.exists()) outputFile.delete()
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(1))
    val lines = ssc.socketTextStream(ip, port, StorageLevel.MEMORY_ONLY_SER)
    val words = lines.flatMap(_.split(" "))
    val wordsCount = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordsCount.foreachRDD((rdd: RDD[(String, Int)], time: Time) => {
      val counts = "Counts at time " + time + " " + rdd.collect().mkString("[", ",", "]")
      println(counts)
      println("Appending to " + outputFile.getAbsolutePath)
      Files.append(counts + "\n", outputFile, Charset.defaultCharset())
    })
    ssc
  }

  private object IntParam {
    def unapply(str: String): Option[Int] = {
      try {
        Some(str.toInt)
      } catch {
        case e: NumberFormatException => None
      }
    }
  }

  def main(args: Array[String]): Unit = {
    //getOrCreate方法，从checkpoint中重新创建StreamingContext对象或新创建一个StreamingContext对象
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createContext(ip, port, outputPath, checkpointDirectory)
      })
    ssc.start()
    ssc.awaitTermination()
  }
}
