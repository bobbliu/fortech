package com.liubo.base._04implicit

object ImplicitParam {
  def main(args: Array[String]): Unit = {
    //隐式参数：在一个方法的参数名前加上 implicit 关键字
    implicit val a = 2
    implicit val b = "B"

    def fun(implicit x: Int, y: String) = {
      x + y
    }
    //如果我们不提供相应的参数，那么方法会自动带入当前作用域内
    // 的带有 implicit 关键字的变量作为参数。编译器会根据类型去匹配
    println(fun)
    println(fun(1, "A"))
  }
}
