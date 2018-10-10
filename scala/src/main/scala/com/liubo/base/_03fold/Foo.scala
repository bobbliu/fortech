package com.liubo.base._03fold

/**
  * foldLeft
  *这里也有个初始值，这里是一个空的String list，也有一个操作函数。
  * 在本例中，我们判断了性别，并构造了我们想要的String，并追加到累加器中（这里是一个list）。
  */

class Foo(val name: String, val age: Int, val sex: Symbol)

object Foo {
  def apply(name: String, age: Int, sex: Symbol): Foo = new Foo(name, age, sex)

  def main(args: Array[String]): Unit = {
    val fooList = Foo("Hugh class", 25, 'male) ::
      Foo("Biggus Dickus", 43, 'male) ::
      Foo("Incontinentia Buttocks", 37, 'female) ::
      Nil

    val stringList = fooList.foldLeft(List[String]()) { (z, f) =>
      val title = f.sex match {
        case 'male => "Mr."
        case 'female => "Ms."
      }
      z :+ s"$title ${f.name},${f.age}"
    }

    println(stringList)
  }
}