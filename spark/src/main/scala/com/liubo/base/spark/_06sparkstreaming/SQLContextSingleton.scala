package com.liubo.base.spark._06sparkstreaming

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object SQLContextSingleton {
  @transient private var instance: SQLContext = _

  def getInstance(sparkContext: SparkContext): SQLContext = {
    if (instance == null) {
      instance = new SQLContext(sparkContext)
    }
    instance
  }
}

