package morning.cat.kafka.client.kryo_test;

import morning.cat.kafka.client.domain.Person;
import morning.cat.kafka.client.interceptor.EmptyProducerInterceptor;
import morning.cat.kafka.client.interceptor.PersonProducerInterceptor;
import morning.cat.kafka.client.serialize.PersonUseKryoSerializer;
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

    public static final String TOPIC = "helloTopic5";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PersonUseKryoSerializer.class.getName());
        // 配置生产者拦截器
        // 如果配置多个拦截器，则按照顺序依次执行
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, Arrays.asList(EmptyProducerInterceptor.class, PersonProducerInterceptor.class).stream().map(t -> t.getName()).collect(Collectors.joining(",")));
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
