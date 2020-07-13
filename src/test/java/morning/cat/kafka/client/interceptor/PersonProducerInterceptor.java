package morning.cat.kafka.client.interceptor;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;

import java.util.Map;

/**
 * @describe: {@link Person} 的生产者拦截器实现
 * @author: morningcat.zhang
 * @date: 2020/7/13 4:25 PM
 * @Version 1.0
 */
public class PersonProducerInterceptor implements ProducerInterceptor<String, Person> {

    /**
     * KafkaProducer 在将消息序列化和计算分区之前会调用生产者拦截器的 onSend() 方法来对消息进行相应的定制化操作
     */
    @Override
    public ProducerRecord<String, Person> onSend(ProducerRecord<String, Person> record) {
        String topic = record.topic(); // 主题
        Integer partition = record.partition(); // 分区
        Long timestamp = record.timestamp(); // 时间戳
        String key = record.key();
        Person value = record.value();
        Headers headers = record.headers(); //

        // 定制化需求
        value.setName("Interceptor-" + value.getName());

        return new ProducerRecord<>(topic, partition, timestamp, key, value, headers);
    }

    /**
     * KafkaProducer 会在消息被应答之前或消息发送失败时调用生产者拦截器的 onAcknowledgement() 方法，优先于用户设定的 Callback 之前执行
     * 此方法运行在 KafkaProducer 的 I/O 线程中
     *
     * @param metadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception != null) {

            String topic = metadata.topic(); // 主题
            Integer partition = metadata.partition(); // 分区
            Long offset = metadata.offset(); // 偏移量
            String exceptionMsg = exception.getMessage();
            System.out.println(String.format("%s:%s:%s:%s:%s", "PersonProducerInterceptor:onAcknowledgement", topic, partition, offset, exceptionMsg));
            // logger.error
        }
    }

    /**
     * 关闭拦截器时执行一些资源清理工作
     */
    @Override
    public void close() {
        System.out.println("ProducerInterceptor.close");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
