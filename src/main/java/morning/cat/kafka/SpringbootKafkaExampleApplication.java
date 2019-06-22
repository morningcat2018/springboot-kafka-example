package morning.cat.kafka;

import morning.cat.kafka.domain.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * link https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-kafka/src/main/java/sample/kafka
 */
@SpringBootApplication
public class SpringbootKafkaExampleApplication {

    public static void main(String[] args) {


        SpringApplication.run(SpringbootKafkaExampleApplication.class, args);
    }

    @Value("${demo.kafaka.topic.test}")
    String testTopic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Bean
    public void send() {
        for (int i = 0; i < 3; i++) {
            Message message = Message.builder().id(i).msg("This is a test msg").build();
            kafkaTemplate.send(testTopic, message);
        }
    }

    @KafkaListener(topics = "${demo.kafaka.topic.test}")
    public void receive(ConsumerRecord<String, Message> consumerRecord) {
        System.out.println(consumerRecord.topic() + " --> " + consumerRecord.value());
    }
}

