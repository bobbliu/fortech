package com.liubo.base._04implicit

/**
  * Scala 编译器仅考虑处于作用域之内的隐式转换。可以使用 import 关键字访问其它库的隐式转换。
  * 编译器还将在源类型或转化的期望目标类型的伴生对象中寻找隐式定义。
  *
  * 这样可以很方便的在当前作用域内引入我们需要的隐式转换。
  */

import com.liubo.base._04implicit.MyConversations.String2Int

object ImportOtherImplicit {
  //命名隐式转换
  val x: Int = "12"

  def main(args: Array[String]): Unit = {
    println(x)
  }
}
