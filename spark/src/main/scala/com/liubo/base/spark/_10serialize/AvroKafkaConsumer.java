package com.liubo.base.spark._10serialize;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *  * Created by https://www.iteblog.com on 2017-09-20.
 *  
 */
public class AvroKafkaConsumer {
    Logger logger = LoggerFactory.getLogger("AvroKafkaConsumer");
    private final ConsumerConnector consumer;
    private final String topic;

    public AvroKafkaConsumer(String zookeeper, String groupId, String topic) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "500");
        props.put("zookeeper.sync.time.ms", "250");
        props.put("auto.commit.interval.ms", "1000");

        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        this.topic = topic;
    }

    public void testConsumer() {
        Map<String, Integer> topicCount = new HashMap<>();
        topicCount.put(topic, 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumer.createMessageStreams(topicCount);
        List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);
        for (final KafkaStream stream : streams) {
            ConsumerIterator it = stream.iterator();
            while (it.hasNext()) {
                MessageAndMetadata messageAndMetadata = it.next();
                String key = new String((byte[]) messageAndMetadata.key());
                byte[] message = (byte[]) messageAndMetadata.message();

                Schema.Parser parser = new Schema.Parser();
                Schema schema = parser.parse(AvroKafkaProducter.USER_SCHEMA);
                Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
                GenericRecord record = recordInjection.invert(message).get();
                logger.info("key=" + key + ", str1= " + record.get("str1")
                        + ", str2= " + record.get("str2")
                        + ", int1=" + record.get("int1"));
                System.out.println("key=" + key + ", str1= " + record.get("str1")
                        + ", str2= " + record.get("str2")
                        + ", int1=" + record.get("int1"));
            }
        }
        consumer.shutdown();
    }

    public static void main(String[] args) {
        AvroKafkaConsumer simpleConsumer =
                new AvroKafkaConsumer("cdh-node1:2181,cdh-node2:2181,cdh-node3:2181", "good", "con");
        simpleConsumer.testConsumer();
    }
}
