package morning.cat.kafka.client.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/10 3:31 PM
 * @Version 1.0
 */
public class KafkaProducerTest {

    public static final String TOPIC = "helloTopic";

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("bootstrap.servers", "localhost:9092");
        StringSerializer stringSerializer = new StringSerializer();
        KafkaProducer<String, String> producer = new KafkaProducer(map, stringSerializer, stringSerializer);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "my key", "my message");
        producer.send(record);

//        Person person = new Person();
//        person.setName("zizi");
//        ProducerRecord<String, String> record2 = new ProducerRecord<>(TOPIC, person.toString());
//        producer.send(record2);

        producer.close();
    }
}
