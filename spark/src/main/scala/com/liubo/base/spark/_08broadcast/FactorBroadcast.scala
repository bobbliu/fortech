package com.liubo.base.spark._08broadcast

import org.apache.spark.sql.SparkSession
//import org.slf4j.LoggerFactory
//import org.apache.log4j.{Level, Logger}

import scala.collection.mutable.ListBuffer

object FactorBroadcast {

  def main(args: Array[String]): Unit = {
    val starttime = System.currentTimeMillis()
    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("FactorBroadcast")
      .getOrCreate()
    //    sparkSession.sparkContext.setLogLevel("ERROR")
    //    val logger = LoggerFactory.getLogger(this.getClass.getName)
    //    Logger.getLogger("org.apache.hadoop").setLevel(Level.OFF)
    //    Logger.getLogger("org.apache.hadoop").setLevel(Level.OFF)


    val factor = List[Int](1, 2, 3)
    val factorBroadcast = sparkSession.sparkContext.broadcast(factor)

    //    val nums = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val nums = Array(1 to 99).flatMap(x => x)
    val numsRdd = sparkSession.sparkContext.parallelize(nums, 11)
    //numsRdd.cache()

    val list = new ListBuffer[List[Int]]
    val resRdd = numsRdd.mapPartitions(ite => {
      while (ite.hasNext) {
        list += ite.next() :: (factorBroadcast.value)
      }
      list.iterator
    })
    resRdd.foreach(res => println(res))
    val endtime = System.currentTimeMillis()
    val costtime = endtime - starttime
    println(s"========耗时=======$costtime================")
  }
}
