package morning.cat.kafka.kafka;

import morning.cat.kafka.domain.SampleMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @KafkaListener(topics = "${demo.kafaka.topic.default}")
    public void processMessage(SampleMessage message) {
        System.out.println("Consumer ---> 接收到消息 ----> [" + message + "]");
    }

}
