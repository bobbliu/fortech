package com.liubo.base._04implicit

object MyConversations {
  implicit def String2Int(str: String) = str.toInt

  implicit def Double2Int(num: Double) = num.toInt
}
