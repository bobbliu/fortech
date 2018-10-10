package com.liubo.base.spark._02rdd_transformation

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/*
* RDD 常用Transformation函数
* */

object RDD_Operation {
  val sc = SparkSession.builder()
    .master("local[*]")
    .appName("RDD_Operation")
    .getOrCreate()
  sc.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    val rdd1 = sc.sparkContext.parallelize(1 to 5)
    val rdd2 = sc.sparkContext.parallelize(4 to 8)
    //注意collect只适合数据量较少时使用
    val rdd3 = union_test(rdd1, rdd2).collect()
    val rdd4 = intersection_test(rdd1, rdd2).collect()
    val rdd5 = distinct_test(rdd1, rdd2).collect()
    val rdd6 = groupByKey_test(rdd1, rdd2).collect()
    val rdd7 = reduceByKey_test(rdd1, rdd2).collect()
    val rdd8 = sortByKey_test(rdd1, rdd2).collect()
    println("===============================")
    val rdd9 = sc.sparkContext.parallelize(Array((1, 2), (1, 3)))
    val rdd10 = sc.sparkContext.parallelize(Array((1, 3)))
    val rdd11 = join_test(rdd9, rdd10).collect()
    val rdd12 = leftjoin_test(rdd9, rdd10).collect()
    val rdd13 = rightjoin_test(rdd9, rdd10).collect()
    val rdd14 = cogroup_test(rdd9, rdd10).collect()
    println("===============================")
    val rdd15 = sc.sparkContext.parallelize(Array(1, 2, 3, 4))
    val rdd16 = sc.sparkContext.parallelize(Array(5, 6))
    val rdd17 = cartesian_test(rdd15, rdd16).collect()
    println("===============================")
    val rdd18 = sc.sparkContext.parallelize(1 to 100, 3)
    val rdd19 = coalesce_test(rdd18, 2)
    println("===============================")
    sc.stop()
  }

  /**
    * *RDD并集操作
    **/
  def union_test(rdd1: RDD[Int], rdd2: RDD[Int]): RDD[Int] = {
    return rdd1.union(rdd2)
  }

  /**
    * *RDD交集操作
    **/
  def intersection_test(rdd1: RDD[Int], rdd2: RDD[Int]): RDD[Int] = {
    return rdd1.intersection(rdd2)
  }

  /**
    * *distinct函数将去除重复元素
    **/
  def distinct_test(rdd1: RDD[Int], rdd2: RDD[Int]): RDD[Int] = {
    return rdd1.union(rdd2).distinct()
  }

  /**
    * *groupByKey
    * 输入数据为(K, V) 对, 返回的是 (K, Iterable)
    **/
  def groupByKey_test(rdd1: RDD[Int], rdd2: RDD[Int]): RDD[(Int, Iterable[Int])] = {
    return rdd1.union(rdd2).map((_, 1)).groupByKey()
  }

  /**
    * *reduceByKey
    * reduceByKey函数输入数据为(K, V)对，返回的数据集结果也是（K,V）对，只不过V为经过聚合操作后的值
    **/
  def reduceByKey_test(rdd1: RDD[Int], rdd2: RDD[Int]): RDD[(Int, Int)] = {
    return rdd1.union(rdd2).map((_, 1)).reduceByKey(_ + _)
  }

  /**
    * *sortByKey
    * 对输入的数据集按key排序
    **/
  def sortByKey_test(rdd1: RDD[Int], rdd2: RDD[Int]): RDD[(Int, Int)] = {
    return rdd1.union(rdd2).map((_, 1)).reduceByKey(_ + _).sortByKey(false)
  }

  /**
    * *join
    * 对于数据集类型为 (K, V) 及 (K, W)的RDD，join操作后返回类型为 (K, (V, W))，join函数有三种：
    * def join[W](other: RDD[(K, W)], partitioner: Partitioner): RDD[(K, (V, W))]
    * def leftOuterJoin[W](
    * other: RDD[(K, W)],
    * partitioner: Partitioner): RDD[(K, (V, Option[W]))]
    * def rightOuterJoin[W](other: RDD[(K, W)], partitioner: Partitioner)
    * : RDD[(K, (Option[V], W))]
    **/
  def join_test(rdd1: RDD[(Int, Int)], rdd2: RDD[(Int, Int)]): RDD[((Int, (Int, Int)))] = {
    return rdd1.join(rdd2)
  }

  def leftjoin_test(rdd1: RDD[(Int, Int)], rdd2: RDD[(Int, Int)]): RDD[(Int, (Int, Option[Int]))] = {
    return rdd1.leftOuterJoin(rdd2)
  }

  def rightjoin_test(rdd1: RDD[(Int, Int)], rdd2: RDD[(Int, Int)]): RDD[(Int, (Option[Int], Int))] = {
    return rdd1.rightOuterJoin(rdd2)
  }

  /**
    * *cogroup
    * *如果输入的RDD类型为(K, V) 和(K, W)，则返回的RDD类型为 (K, (Iterable, Iterable)) . 该操作与 groupWith等同
    **/
  def cogroup_test(rdd1: RDD[(Int, Int)], rdd2: RDD[(Int, Int)]): RDD[(Int, (Iterable[Int], Iterable[Int]))] = {
    return rdd1.cogroup(rdd2)
  }

  /**
    * *cartesian
    * *求两个RDD数据集间的笛卡尔积
    **/
  def cartesian_test(rdd1: RDD[(Int)], rdd2: RDD[(Int)]): RDD[((Int, Int))] = {
    return rdd1.cartesian(rdd2)
  }

  /**
    * *coalesce
    * *将RDD的分区数减至指定的numPartitions分区数
    * repartition(numPartitions)，功能与coalesce函数相同，实质上它调用的就是coalesce函数，只不是shuffle = true，意味着可能会导致大量的网络开销。
    **/
  def coalesce_test(rdd1: RDD[(Int)], num: Int): RDD[(Int)] = {
    return rdd1.coalesce(num)
  }
}
