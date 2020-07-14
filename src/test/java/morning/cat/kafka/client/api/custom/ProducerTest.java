package morning.cat.kafka.client.api.custom;

import morning.cat.kafka.client.domain.Person;
import morning.cat.kafka.client.interceptor.EmptyProducerInterceptor;
import morning.cat.kafka.client.interceptor.PersonProducerInterceptor;
import morning.cat.kafka.client.serialize.PersonSerializer;
import morning.cat.kafka.client.serialize.kryo.PersonUseKryoSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @describe: 消息生产者
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:40 AM
 * @Version 1.0
 */
public class ProducerTest {

    public static final String TOPIC = "helloTopic6";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PersonSerializer.class.getName());
        KafkaProducer<String, Person> producer = new KafkaProducer(properties);

        Person person = new Person();
        person.setId(44456L);
        person.setName("李思");
        person.setPersonNo("G7704HZ057XC");
        ProducerRecord<String, Person> record = new ProducerRecord<>(TOPIC, person.getPersonNo(), person);
        producer.send(record);

        producer.close();
    }
}
