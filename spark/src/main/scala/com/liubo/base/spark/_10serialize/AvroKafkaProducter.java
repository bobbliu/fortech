package com.liubo.base.spark._10serialize;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 *  * Created by yangping.wu on 2017-07-20.
 *  
 */
public class AvroKafkaProducter {
    Logger logger = LoggerFactory.getLogger("AvroKafkaProducter");
    public static final String USER_SCHEMA = "{"
            + "\"type\":\"record\","
            + "\"name\":\"con\","
            + "\"fields\":["
            + "{ \"name\":\"str1\", \"type\":\"string\" },"
            + "{ \"name\":\"str2\", \"type\":\"string\" },"
            + "{ \"name\":\"int1\", \"type\":\"int\" }"
            + "]}";

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "cdh-node1:9092,cdh-node2:9092,cdh-node3:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(USER_SCHEMA);
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);

        KafkaProducer<String, byte[]> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 1000; i++) {
            GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("str1", "Str 1-" + i);
            avroRecord.put("str2", "Str 2-" + i);
            avroRecord.put("int1", i);

            byte[] bytes = recordInjection.apply(avroRecord);

            ProducerRecord<String, byte[]> record = new ProducerRecord<>("con", "" + i, bytes);
            System.out.println("topic->" + "con" + "  " + "key->" + i + "  " + "message->" + bytes);
            producer.send(record);
            Thread.sleep(250);

        }

        producer.close();
    }
}
