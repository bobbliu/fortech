package com.liubo.base.spark._09sparkpersisit

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.SparkSession

/**
  * 使用Spark广播变量（excutor不可改变，只能读取）
  *
  * 需求：
  * 用户表：
  * id name age gender(0|1)
  * 要求，输出用户信息，gender必须为男或者女，不能为0,1
  *
  */

object SparkBroadcastOps {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("SparkBroadcastOps")
      .getOrCreate()

    val sc = sparkSession.sparkContext

    val userList = List(
      "001,刘向前,18,0",
      "002,冯剑,28,1",
      "003,李志杰,38,0",
      "004,郭1鹏,48,2",
      "005,郭2鹏,48,1",
      "006,郭3鹏,48,2",
      "007,郭4鹏,48,0",
      "008,郭5鹏,48,1"
    )
    val genderMap = Map("0" -> "女", "1" -> "男")
    val genderMapBC: Broadcast[Map[String, String]] = sc.broadcast(genderMap)

    val userRDD = sc.parallelize(userList)
    val retRDD = userRDD.map(info => {
      //"001,刘向前,18"
      val prefix = info.substring(0, info.lastIndexOf(","))
      val gender = info.substring(info.lastIndexOf(",") + 1)
      val genderMapValue = genderMapBC.value
      val newGender = genderMapValue.getOrElse(gender, "保密")
      prefix + "," + newGender
    })
    retRDD.foreach(println)
    sc.stop()
  }
}
