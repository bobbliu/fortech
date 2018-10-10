package com.liubo.base._01caseclass

/**
  * *case类：
  * 1.创建对象时，可以省略new关键字
  * 2.内部自动实现了apply、unapply方法
  * 3.可以通过模式匹配。获取类属性
  * 4.类的构造参数实现了getter方法，但是默认是val类型，需设置构造参数类型为var，才能实现setter方法
  * 5.此外，还实现了toString、equals、copy、hashCode方法
  **/

object CaseClass {
  def main(args: Array[String]): Unit = {
    val p = People("zhangsan", 33)
    p.name = "lisi"
    p.age = 23
    println("name:"+s"${p.name} \nage:"+s"${p.age}")
  }

  case class People(var name: String, var age: Int)
}
