package com.liubo.base._05per

object ParBenchmark {

  case class Result(item: Int, c: Long, p: Long) {
    override def toString = "%-10s\t%-10d\t%-10d".format(item, c, p)
  }

  def time(proc: => Any) = {
    def curr = System.currentTimeMillis

    val s = curr;
    proc;
    curr - s
  }

  def even(i: Int) = i % 2 == 0

  def b(count: Int) = Some((1 to count).toList).
    map(it => (it, it.par)).headOption.
    map { it =>
      Result(it._1.size, time(it._1 filter even), time(it._2 filter even))
    }

  def main(args: Array[String]): Unit = {
    println("item\tcommon\tpar")
    Array(1, 2, 5, 10, 12, 15, 18, 20).map(_ * 1000000).
      foreach { it =>
        Runtime.getRuntime.gc()
        Thread.sleep(2000)

        println(b(it).get)
      }
  }
}
