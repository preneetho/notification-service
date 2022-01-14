package com.upgrad.sweethome.notification.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * Class to consume message from Topic and print on console
 */

@Component
public class MessageConsumer implements CommandLineRunner {


    @Value("${messaging-server}")
    private String messagingServer;

    @Value("${topic-name}")
    private String topicName;

    public void consumeMessages() {

        System.out.println(messagingServer + " " + topicName);

        //Consumer Properties
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", messagingServer);
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));

        //Prints the topic subscription list
        Set<String> subscribedTopics = consumer.subscription();
        subscribedTopics.stream().forEach(System.out::println);


        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        } finally {
            consumer.close();
        }
    }


    @Override
    public void run(String... args) throws Exception {

        consumeMessages();
    }
}