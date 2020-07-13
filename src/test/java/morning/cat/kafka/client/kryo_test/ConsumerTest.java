package morning.cat.kafka.client.kryo_test;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:40 AM
 * @Version 1.0
 */
public class ConsumerTest {
    public static final String TOPIC = "helloTopic5";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "morning.cat.kafka.client.serialize.PersonUseKryoDeserializer");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "no2"); // 分组
        KafkaConsumer<String, Person> consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singleton(TOPIC));

        while (true) {
            try {
                ConsumerRecords<String, Person> records = consumer.poll(Duration.ofMillis(1000));
                if (!records.isEmpty()) {
                    records.forEach(record -> {
                        String key = record.key();
                        System.out.println(key);
                        Person person = record.value();
                        System.out.println(person);
                    });
                    System.out.println("============");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
