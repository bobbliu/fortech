package com.liubo.base._03fold

/*

/**
  * fold, foldLeft, and foldRight之间的区别
  * 主要的区别是fold函数操作遍历问题集合的顺序。foldLeft是从左开始计算，然后往右遍历。foldRight是从右开始算，
  * 然后往左遍历。而fold遍历的顺序没有特殊的次序。来看下这三个函数的实现吧（在TraversableOnce特质里面实现）
  */

object DifferenceOfFold {
  def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 = foldLeft(z)(op)

  def foldLeft[B](z: B)(op: (B, A) => B): B = {
    var result = z
    this.seq foreach (x => result = op(result, x))
    result
  }

  def foldRight[B](z: B)(op: (A, B) => B): B =
    reversed.foldLeft(z)((x, y) => op(y, x))

  /**
    * 由于fold函数遍历没有特殊的次序，所以对fold的初始化参数和返回值都有限制。在这三个函数中，初始化参数和返回值的参数类型必须相同。
    * 第一个限制是初始值的类型必须是list中元素类型的超类。在我们的例子中，我们的对List[Int]进行fold计算，而初始值是Int类型的，它是List[Int]的超类。
    * 第二个限制是初始值必须是中立的(neutral)。也就是它不能改变结果。比如对加法来说，中立的值是0；而对于乘法来说则是1，对于list来说则是Nil。
    * 顺便说下，其实foldLeft和foldRight函数还有两个缩写的函数：
    */

  def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

  def :\[B](z: B)(op: (A, B) => B): B = foldRight(z)(op)

  (0 /: (1 to 100)) (_ + _)

  ((1 to 100) :\ 0) (_ + _)
}
*/