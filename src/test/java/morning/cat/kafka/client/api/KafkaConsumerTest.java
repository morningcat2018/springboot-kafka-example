package morning.cat.kafka.client.api;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/10 5:03 PM
 * @Version 1.0
 */
public class KafkaConsumerTest {
    public static final String TOPIC = "helloTopic";

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("bootstrap.servers", "localhost:9092");
        map.put("group.id", "no1"); // 分组
        StringDeserializer stringDeserializer = new StringDeserializer();
        KafkaConsumer<String, String> consumer = new KafkaConsumer(map, stringDeserializer, stringDeserializer);
        consumer.subscribe(Collections.singleton(TOPIC));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
//            if (!records.isEmpty()) {
//                records.forEach(record -> System.out.println(record.key() + " " + record.value()));
//            }
        }

    }
}
