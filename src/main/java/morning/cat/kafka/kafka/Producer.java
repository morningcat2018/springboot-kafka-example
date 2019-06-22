
package morning.cat.kafka.kafka;

import morning.cat.kafka.domain.SampleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${demo.kafaka.topic.default}")
    String topicName;

    public void send(SampleMessage message) {
        this.kafkaTemplate.send(topicName, message);
        System.out.println("发送消息 [" + message + "]");
    }


}
