package com.liubo.base.spark._05sparksql

import org.apache.spark.sql.SparkSession

/*
* sparkContext->RDD
* streamingContext->DStream
* sqlContext->DataFrame
* */

object SparkSQL {
  val sparksession = SparkSession.builder()
    .master("local[*]")
    .appName("SparkSQL")
    .getOrCreate()
  sparksession.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    val df = sparksession.sqlContext.read.json("hdfs://cdh-node1:8020/tmp/a.json")
    df.printSchema() //查看DataFrame元数据信息
    df.show() //显示DataFrame完整信息
    df.select("name").show() //返回DataFrame某列所有数据
    df.filter(df("age") > 21).show() //DataFrame数据过滤
    df.groupBy("age").count().show() //按年龄分组

    //df.registerTempTable("People")//注册成表
    df.createOrReplaceTempView("People")//注册成视图
    println(sparksession.sqlContext.isCached("People"))//注册成的表在哪？
    val sql_teenagers = sparksession.sqlContext.sql("SELECT name, age FROM people WHERE age >13 and age <=19")//执行SparkSQL
    val teenagers_rdd = sql_teenagers.rdd
    teenagers_rdd.map(t => "name: " + t(0)+"\n"+"age: "+t(1)).collect().foreach(println) //DataFrame.rdd 可以将dataFrame转为RDD类型
    println("===============================================")
    sparksession.stop()
  }
}
