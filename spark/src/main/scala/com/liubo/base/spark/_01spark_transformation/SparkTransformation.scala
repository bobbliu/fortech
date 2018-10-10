package com.liubo.base.spark._01spark_transformation

import org.apache.spark.sql.SparkSession

/*
* RDD编程模型的核心思想：将RDD转换成新的RDD
* RDD的操作包括Transformations（转换)、Actions两种。
* 需要特别注意的是所有的transformation都是lazy的，如果对scala中的lazy了解的人都知道，
* transformation之后它不会立马执行，而只是会记住对相应数据集的transformation，而到
* 真正被使用的时候才会执行。
* action是解决程序最终执行的诱因，action操作会返回程序执行结果。如collect操作或将运行结果保存
* */

object SparkTransformation {
  val sc = SparkSession.builder()
    .master("local[*]")
    .appName("RDD_Operation")
    .getOrCreate()
  sc.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    /**
      * map
      * Return a new RDD by applying a function to all elements of this RDD.
      * def map[U: ClassTag](f: T => U): RDD[U]
      */
    val rdd1 = sc.sparkContext.parallelize(Array(1, 2, 3, 4)).map(x => x * 2)

    /**
      * filter
      * Return a new RDD containing only the elements that satisfy a predicate.
      * def filter(f: T => Boolean): RDD[T]
      */
    val rdd2 = sc.sparkContext.parallelize(Array(1, 2, 3, 4)).filter(x => x > 1).collect()

    /**
      * flatmap
      * Return a new RDD by first applying a function to all elements of this RDD, and then flattening the results.
      * def flatMap[U: ClassTag](f: T => TraversableOnce[U]): RDD[U]
      */
    val data = Array(Array(1, 2, 3, 4, 5), Array(4, 5, 6))
    val rdd3 = sc.sparkContext.parallelize(data)
    val rdd4 = rdd3.flatMap(x => x.map(y => y)).collect()

    /**
      * mapPartitions(func)
      * mapPartitions是map的一个变种。map的输入函数是应用于RDD中每个元素，
      * 而mapPartitions的输入函数是应用于每个分区，也就是把每个分区中的内容作
      * 为整体来处理的。它的函数定义为：
      * def mapPartitions[U: ClassTag](f: Iterator[T] => Iterator[U], preservesPartitioning: Boolean = false): RDD[U]
      * f即为输入函数，它处理每个分区里面的内容。每个分区中的内容将以Iterator[T]
      * 传递给输入函数f，f的输出结果是Iterator[U]。最终的RDD由所有分区经过输入
      * 函数处理后的结果合并起来的。
      */
    val a = sc.sparkContext.parallelize(1 to 9, 3)

    val b = a.mapPartitions(myfunc).collect

    /**
      * mapPartitionsWithIndex
      * mapPartitionsWithIndex函数是mapPartitions函数的一个变种，它的函数参数如下：
      * def mapPartitionsWithIndex[U: ClassTag](
      * f: (Int, Iterator[T]) => Iterator[U],
      * preservesPartitioning: Boolean = false): RDD[U]
      */
    val c = a.mapPartitionsWithIndex(yourfunc).collect

    /*
    * sample
    * Return a sampled subset of this RDD.
   *
   * @param withReplacement can elements be sampled multiple times (replaced when sampled out)
   * @param fraction expected size of the sample as a fraction of this RDD's size
   *  without replacement: probability that each element is chosen; fraction must be [0, 1]
   *  with replacement: expected number of times each element is chosen; fraction must be >= 0
   * @param seed seed for the random number generator
   *1、withReplacement：元素可以多次抽样(在抽样时替换)
   *2、fraction：期望样本的大小作为RDD大小的一部分，
   * 当withReplacement=false时：选择每个元素的概率;分数一定是[0,1] ；
   * 当withReplacement=true时：选择每个元素的期望次数; 分数必须大于等于0。
   *3、seed：随机数生成器的种子
   * def sample(
      withReplacement: Boolean,
      fraction: Double,
      seed: Long = Utils.random.nextLong): RDD[T]
    * */

    // 元素可以多次抽样：withReplacement=true，每个元素被抽取到的期望次数为2：fraction=2
    val smapledA = a.sample(true, 0.5)
    val d = a.sample(true, 0.5).collect

    val smapledB = a.sample(false, 0.5)
    val e = a.sample(false, 0.5).collect
    println("===========================================")
    sc.stop()
  }

  /*
  * myfunc是把分区中一个元素和它的下一个元素组成一个Tuple。
  * */
  def myfunc[T](iter: Iterator[T]): Iterator[(T, T)] = {
    var res = List[(T, T)]()
    var pre = iter.next()
    while (iter.hasNext) {
      val cur = iter.next()
      res :+= (pre, cur) //将元素添加到List的尾部（:+）
      pre = cur
    }
    res.iterator
  }

  /*
  *函数带分区索引，返回的集合第一个元素为分区索引
  * */
  def yourfunc[T](index: T, iter: Iterator[T]): Iterator[(T, T, T)] = {
    var res = List[(T, T, T)]()
    var pre = iter.next()
    while (iter.hasNext) {
      val cur = iter.next()
      res :+= (index, pre, cur)
      pre = cur
    }
    res.iterator
  }
}
