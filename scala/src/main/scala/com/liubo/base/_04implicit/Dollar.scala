package com.liubo.base._04implicit

/**
  * 编译器还将在源类型或转化的期望目标类型的伴生对象中寻找隐式定义。
  * 无歧义规则：隐式转换唯有在不存在其它可插入转换的前提下才能插入。
  *
  * @param num
  */

case class Euro(num: Int)

case class Dollar(num: Int) {
  def +(d: Dollar) = num + d.num
}

object Dollar {
  implicit def EuroToDollar(euro: Euro): Dollar = Dollar(2 * euro.num)

  def main(args: Array[String]): Unit = {
    print(Dollar(3) + Euro(10))
  }
}




