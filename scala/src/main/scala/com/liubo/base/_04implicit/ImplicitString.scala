package com.liubo.base._04implicit

object ImplicitString {

  /**
    * 对String类型数据的隐式转换
    * 作用域规则：插入的隐式转换必须以单一标识符的形式处于作用域中，或与转换的源或目标类型关联在一起。
    * 只要数据源类型一致，可将其转换成其他任何类型
    *
    * 单一调用原则：只会尝试一个隐式操作。
    * 编译器不会对某个变量执行多次的隐式转换。
    * 显式操作先行原则：若编写的代码类型检查无误，则不会尝试任何隐式操作。
    * 也就是说编译器并不会优先考虑我们定义的隐式操作。
    *
    */

  /*  implicit def String2Int(str: String) :Int= {
      str.toInt
    }*/

  implicit def String2Int(str: String) = {
    str.toDouble
  }

  def main(args: Array[String]): Unit = {
    println("120" / 3)
    println("120" + 3)
  }
}
