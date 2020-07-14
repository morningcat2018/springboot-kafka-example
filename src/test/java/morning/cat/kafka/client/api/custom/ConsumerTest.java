package morning.cat.kafka.client.api.custom;

import morning.cat.kafka.client.domain.Person;
import morning.cat.kafka.client.serialize.PersonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @describe: 客户端消费者
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:40 AM
 * @Version 1.0
 */
public class ConsumerTest {
    public static final String TOPIC = "helloTopic6";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PersonDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put("group.id", "no3"); // 分组
        KafkaConsumer<String, Person> consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singleton(TOPIC));

        while (true) {
            try {
                ConsumerRecords<String, Person> records = consumer.poll(Duration.ofMillis(1000));
                if (!records.isEmpty()) {
                    records.forEach(record -> {
                        System.out.println(record.key());
                        System.out.println(record.value());
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
