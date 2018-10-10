package com.liubo.base.spark._xxtestdemo

import org.apache.spark.sql.SparkSession

/**
  * 计算ratings.dat和movies.dat两个文件中，平均得分超过4.0份的电影列表
  */

object SparkJoin {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("SparkJoin")
      .getOrCreate()
    val sc = sparkSession.sparkContext

    // Read rating from HDFS file
    val textFIle = sc.textFile("hdfs://cdh-node1:8020/tmp/data.txt/ratings.dat")

    //extract (movieid, rating)
    val raiting = textFIle.map(line => {
      val fileds = line.split("::")
      (fileds(1).toInt, fileds(2).toDouble) //1:电影id,2:电影得分
    })
    println(s"raiting处理后的数据:电影id+电影得分============================")
    println(raiting.count)

    val movieScores = raiting
      .groupByKey()
      .map(data => {
        val avg = data._2.sum / data._2.size
        (data._1, avg)
      })
    println(s"raiting处理后的数据:电影id+电影平均得分============================")
    println(movieScores.count)

    // Read movie from HDFS file
    val movies = sc.textFile("hdfs://cdh-node1:8020/tmp/data.txt/movies.dat")
    val moviesKey = movies.map(line => {
      //构造一个map存放key为电影id，value为电影id+电影名称
      val fileds = line.split("::")
      (fileds(0).toInt, fileds(1)) //0:电影id,1:电影名称
    }).keyBy(tup => tup._1) //电影id升序排序
    println(s"movies处理后的数据:电影id+电影名称，根据电影id排序============================")
    println(moviesKey.count)

    // by join, we get <movieid, averageRating, movieName>
    val resultp = movieScores
      .keyBy(tup => tup._1) //电影id升序排序
      .join(moviesKey) //join后的结构(x,((x,y),(x,z)))
    println(s"join前评分数据根据电影id排序，join后的数据============================")
    println(resultp.count)

    //把最终结果转换为电影id,电影平均评分,电影名称
    val result = resultp
      .filter(f => f._2._1._2 > 4.0)
      .map(f => (f._1, (f._2._1._2, f._2._2._2)))
      .sortByKey() //电影id升序排序,分区排序

    val arr_res = resultp
      .filter(f => f._2._1._2 > 4.0)
      .map(f => (f._1, (f._2._1._2, f._2._2._2)))
      .collect().sortBy(top => top._1)//全局排序

    println(s"最终结果============================")
    //result.foreach(println)//分区排序结果
    arr_res.foreach(println)//全局排序结果
    sc.stop()
  }
}
