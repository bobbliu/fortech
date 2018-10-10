package com.liubo.base._04implicit

case class Person(name: String, age: Int) {
  def +(x: Int) = age + x

  def +(p: Person) = age + p.age
}

object Person {
  implicit def Add1(x: Int) = Person("Empty", x)

  implicit def Add2(x: Person) = x.age + 1

  def main(args: Array[String]): Unit = {
    val person = Person("xiaohong", 1)
    println(person + 1)
    println(1 + person)
  }
}
