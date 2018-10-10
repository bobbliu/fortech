package com.liubo.base._03fold

/**
  * 通过【foldLeft】来判断一个List是否是另一个List的子集：
  */
object ListContains {
  def main(args: Array[String]): Unit = {
    val list1 = ("电影 刘德华").split("-1").toList
    val list2 = ("醉拳 喜剧 刘德华 刘家良 电影 中国香港").split("-1").toList
    println(list2ContainsList1(list1, list2))
    println(otherlist2ContainsList1(list1, list2))
  }

  /**
    * 判断list1是否是list2的子集
    *
    * @param list1
    * @param list2
    * @return
    */
  def list2ContainsList1(list1: Seq[String], list2: Seq[String]): Boolean = {
    val initalBoolean = true
    (initalBoolean /: list1) { (x, y) => x && list2.contains(y) } //foldLest简写："/:"
  }

  def otherlist2ContainsList1(list1: Seq[String], list2: Seq[String]): Boolean = {
    var initalBoolean = true
    list1.foreach { x =>
      if (!list2.contains(x)) {
        initalBoolean = false
      }
    }
    initalBoolean
  }
}
