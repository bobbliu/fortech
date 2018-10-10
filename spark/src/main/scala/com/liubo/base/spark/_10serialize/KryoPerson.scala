package com.liubo.base.spark._10serialize

import com.esotericsoftware.kryo.io.Input
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.hadoop.io.{BytesWritable, NullWritable}
import org.apache.spark.rdd.RDD
import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.ClassTag

// user defined class that need to serialized
case class KryoPerson(val name: String)

object KryoPerson {
  def main(args: Array[String]) {
    /*    if (args.length < 1) {
          println("Please provide output path")
          return
        }*/
    val outputPath = "C:\\Users\\liubo\\Desktop\\kryo"
    val conf = new SparkConf().setMaster("local").setAppName("kryoexample")
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    //create some dummy data
    val personList = 1 to 100 map (value => new KryoPerson(value + ""))
    val personRDD = sc.makeRDD(personList)
    saveAsObjectFile(personRDD, outputPath)
    val rdd = objectFile[KryoPerson](sc, outputPath)
    println(s"========>${rdd.map(person => person.name).collect().toList}")
  }

  def saveAsObjectFile[T: ClassTag](rdd: RDD[T], path: String) {
    val kryoSerializer = new KryoSerializer(rdd.context.getConf)

    rdd.mapPartitions(iter => iter.grouped(10)
      .map(_.toArray))
      .map(splitArray => {
        //initializes kyro and calls your registrator class
        val kryo = kryoSerializer.newKryo()

        //convert data to bytes
        val bao = new ByteArrayOutputStream()
        val output = kryoSerializer.newKryoOutput()
        output.setOutputStream(bao)
        kryo.writeClassAndObject(output, splitArray)
        output.close()

        // We are ignoring key field of sequence file
        val byteWritable = new BytesWritable(bao.toByteArray)
        (NullWritable.get(), byteWritable)
      }).saveAsSequenceFile(path)
  }

  def objectFile[T](sc: SparkContext, path: String, minPartitions: Int = 1)
                   (implicit ct: ClassTag[T]) = {
    val kryoSerializer = new KryoSerializer(sc.getConf)
    sc.sequenceFile(path, classOf[NullWritable], classOf[BytesWritable],
      minPartitions)
      .flatMap(x => {
        val kryo = kryoSerializer.newKryo()
        val input = new Input()
        input.setBuffer(x._2.getBytes)
        val data = kryo.readClassAndObject(input)
        val dataObject = data.asInstanceOf[Array[T]]
        dataObject
      })
  }
}
