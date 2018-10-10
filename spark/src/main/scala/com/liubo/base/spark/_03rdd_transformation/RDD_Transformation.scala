package com.liubo.base.spark._03rdd_transformation

import org.apache.spark.{HashPartitioner, sql}
import org.apache.spark.sql.SparkSession

object RDD_Transformation {
  val sc = SparkSession.builder()
    .master("local[*]")
    .appName("RDD_Transformation")
    .getOrCreate()
  sc.sparkContext.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {
    /*
  * repartitionAndSortWithinPartitions(partitioner)
  *epartitionAndSortWithinPartitions函数是repartition函数的变种，
  * 与repartition函数不同的是，repartitionAndSortWithinPartitions
  * 在给定的partitioner内部进行排序，性能比repartition要高。
  *
  * Repartition the RDD according to the given partitioner and, within each resulting partition,
  * sort records by their keys.
  *
  * This is more efficient than calling repartition and then sorting within each partition
  * because it can push the sorting down into the shuffle machinery.
  * def repartitionAndSortWithinPartitions(partitioner: Partitioner): RDD[(K, V)]
  */
    val data = List((1, 3), (1, 2), (5, 4), (1, 4), (2, 3), (2, 4))
    val rdd1 = sc.sparkContext.parallelize(data, 3)
    val hash_res = rdd1.repartitionAndSortWithinPartitions(new HashPartitioner(3)).collect()

    /*
    * aggregateByKey(zeroValue)(seqOp, combOp, [numTasks])
    * aggregateByKey函数对PairRDD中相同Key的值进行聚合操作，在聚合过程中同样使用了一个中立的初始值。其函数定义如下：
    * Aggregate the values of each key, using given combine functions and a neutral “zero value”.
    * This function can return a different result type, U, than the type of the values in this RDD,
    * V. Thus, we need one operation for merging a V into a U and one operation for merging two U’s,
    * as in scala.TraversableOnce. The former operation is used for merging values within a
    * partition, and the latter is used for merging values between partitions. To avoid memory
    * allocation, both of these functions are allowed to modify and return their first argument
    * instead of creating a new U.
    * def aggregateByKey[U: ClassTag](zeroValue: U)(seqOp: (U, V) => U, combOp: (U, U) => U): RDD[(K, U)]
    * */
    def seqOp(a: Int, b: Int): Int = {
      println("seq:" + a + "\t" + b)
      math.max(a, b)
    }

    def combineOp(a: Int, b: Int): Int = {
      println("comb:" + a + "\t" + b)
      a + b
    }

    val pair_rdd = sc.sparkContext.parallelize(data)
    val res = pair_rdd.aggregateByKey(1)(seqOp, combineOp).toLocalIterator
    for (i <- res)
      println(i)


    println("===================================================")
    sc.stop()
  }
}