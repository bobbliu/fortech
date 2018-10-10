package com.liubo.base.spark._09sparkpersisit

import org.apache.spark.sql.SparkSession

/**
  * 共享变量
  *
  * 我们在dirver中声明的这些局部变量或者成员变量，可以直接在transformation中使用，
  * 但是经过transformation操作之后，是不会将最终的结果重新赋值给dirver中的对应的变量。
  * 因为通过action，触发了transformation的操作，transformation的操作，都是通过
  * DAGScheduler将代码打包 序列化 交由TaskScheduler传送到各个Worker节点中的Executor去执行，
  * 在transformation中执行的这些变量，是自己节点上的变量，不是dirver上最初的变量，我们只不过是将
  * driver上的对应的变量拷贝了一份而已。
  *
  * 这个案例也反映出，我们需要有一些操作对应的变量，在driver和executor上面共享
  *
  * spark给我们提供了两种解决方案——两种共享变量
  * 广播变量
  * 累加器
  */

object SparkShareVariableOps {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("SparkShareVariableOps")
      .getOrCreate()

    val sc = sparkSession.sparkContext

    val linesRDD = sc.textFile("E:\\data\\wordcount.txt")
    val wordsRDD = linesRDD.flatMap(_.split(" "))
    var num = 0
    val pairsRDD = wordsRDD.map(word => {
      num += 1//RDD中的转换可以使用外部定义的局部或成员变量，它是driver变量的拷贝，与外部变量数值不同
      println("map--->num = " + num)
      (word, 1)
    })
    val retRDD = pairsRDD.reduceByKey(_ + _)
    println(s"======num = $num")
    retRDD.foreach(println)
    println(s"*********num = $num")
    sc.stop()
  }
}
