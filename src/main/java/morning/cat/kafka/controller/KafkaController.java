package morning.cat.kafka.controller;

import morning.cat.kafka.kafka.Producer;
import morning.cat.kafka.domain.SampleMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/17 3:23 PM
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {


    @Autowired
    Producer producer;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Value("${demo.kafaka.topic.record}")
    String recordTopic;

    /**
     * http://127.0.0.1:8080/kafka/send?msg=ffd
     */
    @GetMapping("/send")
    public String sendMsg(@RequestParam("msg") String msg) {
        producer.send(new SampleMessage(System.currentTimeMillis(), msg));
        return "SUCC";
    }

    /**
     * http://127.0.0.1:8080/kafka/send2?msg=ffd
     */
    @GetMapping("/send2")
    public String sendMsg2(@RequestParam("msg") String msg) {
        ProducerRecord<String, String> record = new ProducerRecord(recordTopic, "gouzi", msg);
        kafkaTemplate.send(record);
        return "SUCC";
    }


}
