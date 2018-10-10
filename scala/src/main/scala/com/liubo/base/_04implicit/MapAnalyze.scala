package com.liubo.base._04implicit

object MapAnalyze {
  def main(args: Array[String]): Unit = {
    //数据结构完全一样，去 Predef 库中找到 -> 的实现如下：
    // 并不是什么内建语法，其实就是基于隐式转换。
    //    implicit final class ArrowAssoc[A](private val self: A) extends AnyVal {
    //      @inline def -> [B](y: B): Tuple2[A, B] = Tuple2(self, y)
    //      def →[B](y: B): Tuple2[A, B] = ->(y)
    //    }

    var mp1 = Map(1 -> 2, 3 -> 4)
    var mp2 = Map(Tuple2(1, 2), Tuple2(3, 4))
    var mp3 = Map((1, 2), (3, 4))
    var mp4 = Map(1 → 2, 3 → 4)

    println(s"mp1========>$mp1")
    println(s"mp2========>$mp2")
    println(s"mp3========>$mp3")
    println(s"mp4========>$mp4")

    println(mp1 == mp2)
    println(mp1 == mp3)
    println(mp1 == mp4)

    implicit class MyRange(start: Int) {
      def -->(end: Int) = start to end
    }
    print((1 --> 10).sum) // 55
  }
}
