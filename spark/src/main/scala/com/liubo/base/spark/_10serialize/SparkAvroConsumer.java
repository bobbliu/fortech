package com.liubo.base.spark._10serialize;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import kafka.serializer.DefaultDecoder;
import kafka.serializer.StringDecoder;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.liubo.base.spark._10serialize.AvroKafkaProducter.USER_SCHEMA;

public class SparkAvroConsumer {

    private static Injection<GenericRecord, byte[]> recordInjection;

    static {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(USER_SCHEMA);
        recordInjection = GenericAvroCodecs.toBinary(schema);
    }

    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf()
                .setAppName("SparkAvroConsumer")
                .setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(2000));

        Set<String> topics = Collections.singleton("con");
        Map<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put("metadata.broker.list", "cdh-node1:9092,cdh-node2:9092,cdh-node3:9092");

        JavaPairInputDStream<String, byte[]> directKafkaStream = KafkaUtils.createDirectStream(ssc,
                String.class, byte[].class, StringDecoder.class, DefaultDecoder.class, kafkaParams, topics);
/**
 * 其实在 Spark 中读取 Avro 的消息和之前在 Consumer 篇介绍的过程很类似，也是定义一个模式解析器，
 * 然后再定义一个 Injection ， * 这个对象就可以将读出来的 Avro 消息反序列化成我们需要的数据
 */
//        Schema.Parser parser = new Schema.Parser();
//        Schema schema = parser.parse(USER_SCHEMA);
//        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        directKafkaStream
                .map(message -> recordInjection.invert(message._2).get())
                .foreachRDD(rdd -> {
                    rdd.foreach(record -> {
                        System.out.println("str1= " + record.get("str1")
                                + ", str2= " + record.get("str2")
                                + ", int1=" + record.get("int1"));
                    });
                });

        ssc.start();
        ssc.awaitTermination();
    }
}