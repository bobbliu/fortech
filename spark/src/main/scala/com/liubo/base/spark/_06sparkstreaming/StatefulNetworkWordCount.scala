package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.HashPartitioner
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StatefulNetworkWordCount {

  val sparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("StatefuleNetworkWordCount")
    .getOrCreate()
  sparkSession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {

    //函数字面量，输入的当前值与前一次的状态结果进行累加
    val updateFunc = (values: Seq[Int], state: Option[Int]) => {
      val currentCount = values.sum
      val previousCount = state.getOrElse(0)
      Some(currentCount + previousCount)
    }

    //输入类型为K,V,S,返回值类型为K,S
    //V对应为带求和的值，S为前一次的状态
    val newUpdateFunc = (iterator: Iterator[(String, Seq[Int], Option[Int])]) => {
      iterator.flatMap(t => updateFunc(t._2, t._3).map(s => (t._1, s)))
    }

    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(1))
    ssc.checkpoint(".")

    //初始化RDD结果
    val initialRDD = ssc.sparkContext.parallelize(List(("hello", 1), ("world", 1)))

    //Socket作为数据源，用netcat模拟，nc -lp 9999
    val lines = ssc.socketTextStream("localhost", 9999)

    val words = lines.flatMap(_.split(" "))

    val wordstream = words.map(x => (x, 1))

    val statestream = wordstream.updateStateByKey[Int](newUpdateFunc, new HashPartitioner(ssc.sparkContext.defaultParallelism), true, initialRDD)

    statestream.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
