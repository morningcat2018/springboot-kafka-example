package morning.cat.kafka.client.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/10 4:53 PM
 * @Version 1.0
 */
public class KafkaProducerTest2 {
    public static final String TOPIC = "helloTopic";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<String, String> map = new HashMap<>();
        map.put("bootstrap.servers", "localhost:9092");
        StringSerializer stringSerializer = new StringSerializer();
        KafkaProducer<String, String> producer = new KafkaProducer(map, stringSerializer, stringSerializer);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "my key", "my message" + System.currentTimeMillis());
        //producer.send(record);
        Future<RecordMetadata> future = producer.send(record, (metadata, e) -> {

        });
        RecordMetadata recordMetadata = future.get();
        System.out.println(recordMetadata.toString());

        producer.close();
    }
}
