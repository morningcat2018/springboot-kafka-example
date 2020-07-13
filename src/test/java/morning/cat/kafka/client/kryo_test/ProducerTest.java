package morning.cat.kafka.client.kryo_test;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:40 AM
 * @Version 1.0
 */
public class ProducerTest {

    public static final String TOPIC = "helloTopic5";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "morning.cat.kafka.client.serialize.PersonUseKryoSerializer");
        properties.put("bootstrap.servers", "localhost:9092");
        KafkaProducer<String, Person> producer = new KafkaProducer(properties);

        Person person = new Person();
        person.setId(333L);
        person.setName("zhangsan");
        person.setPersonNo("P00046JJ99" + System.currentTimeMillis());
        ProducerRecord<String, Person> record = new ProducerRecord<>(TOPIC, person.getPersonNo(), person);
        producer.send(record);

        producer.close();
    }
}
