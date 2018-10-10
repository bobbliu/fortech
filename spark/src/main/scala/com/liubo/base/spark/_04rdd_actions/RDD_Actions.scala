package com.liubo.base.spark._04rdd_actions

import org.apache.spark.sql.SparkSession

object RDD_Actions {
  val sc = SparkSession.builder()
    .master("local[*]")
    .appName("RDD_Actions")
    .getOrCreate()
  sc.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    /*
    * collect
    * 返回RDD中所有的数据元素
    * Return an array that contains all of the elements in this RDD.
    * def collect(): Array[T]
    * */

    /*
    * reduce(func)
    * reduce采样累加或关联操作减少RDD中元素的数量
    * Reduces the elements of this RDD using the specified commutative and associative binary operator.
    * def reduce(f: (T, T) => T): T
    * */

    val data = sc.sparkContext.parallelize(1 to 9)
    println(data.reduce((x, y) => x + y))
    //data.reduce(_+_)

    /*
    * count()
    * 返回RDD中的元素个数
    * Return the number of elements in the RDD.
    * def count(): Long
    * */
    println(data.count())

    /*
    * first()
    * 返回RDD的第一个元素
    * Return the first element in this RDD.
    * def first()
    * */

    println(data.first())

    /*
    * take(n)
    *返回RDD，包含RDD中的前n个元素
    * Take the first num elements of the RDD. It works by first scanning one partition, and use the
    * results from that partition to estimate the number of additional partitions needed to satisfy
    * the limit.
    * @note due to complications in the internal implementation, this method will raise
    * an exception if called on an RDD of Nothing or Null.
    * def take(num: Int): Array[T]
    * */
    val rdd2 = data.take(2)

    /*
    * takeSample(withReplacement, num, [seed])
    * 对RDD中的数据进行采样，是否允许重复，采样元素个数
    * Return a fixed-size sampled subset of this RDD in an array
    *
    * @param withReplacement whether sampling is done with replacement
    * @param num size of the returned sample
    * @param seed seed for the random number generator
    * @return sample of specified size in an array
    *def takeSample(
      withReplacement: Boolean,
      num: Int,
      seed: Long = Utils.random.nextLong): Array[T]
    * */

    val rdd3 = data.takeSample(false, 8)
    val rdd4 = data.takeSample(true, 8)

    /*
    * takeOrdered(n, [ordering])
    *
    * Returns the first k (smallest) elements from this RDD as defined by the specified
    * implicit Ordering[T] and maintains the ordering. This does the opposite of [[top]].
    * For example:
    * {{{
    * sc.parallelize(Seq(10, 4, 2, 12, 3)).takeOrdered(1)
    * // returns Array(2)
    *
    * sc.parallelize(Seq(2, 3, 4, 5, 6)).takeOrdered(2)
    * // returns Array(2, 3)
    * }}}
    *
    * @param num k, the number of elements to return
    * @param ord the implicit ordering for T
    * @return an array of top elements
    * def takeOrdered(num: Int)(implicit ord: Ordering[T]): Array[T]
    * */

    /*
    * saveAsTextFile
    * 将RDD保存到文件，本地模式时保存在本地文件，集群模式指，如果在Hadoop则保存在HDFS上
    * Save this RDD as a text file, using string representations of elements.
    * def saveAsTextFile(path: String): Unit
    * */

    // data.saveAsTextFile("hdfs://cdh-node1:8020/tmp/data.txt")

    /*
    *countByKey()
    * 将RDD中的数据按Key计数
    * * Count the number of elements for each key, collecting the results to a local Map.
    * Note that this method should only be used if the resulting map is expected to be small, as
    * the whole thing is loaded into the driver’s memory.
    * To handle very large results, consider using rdd.mapValues(_ => 1L).reduceByKey(_ + _), which
    * returns an RDD[T, Long] instead of a map.
        def countByKey(): Map[K, Long]
    * */

    val list = List((1, 3), (1, 2), (5, 4), (1, 4), (2, 3), (2, 4))
    val rdd5 = sc.sparkContext.parallelize(list, 3)
    val res = rdd5.countByKey()

    /*
    * foreach(func)
    * foreach方法遍历RDD中所有的元素
    * Actions (launch a job to return a value to the user program)
    * Applies a function f to all elements of this RDD.
    * def foreach(f: T => Unit): Unit
    * */
    rdd5.foreach(x => println("key=" + x._1 + ",value=" + x._2))

    println("===================================================")
    sc.stop()
  }
}
