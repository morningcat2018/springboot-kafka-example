package morning.cat.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TopicConfig {
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicName() {
        // 第一个是参数是 topic 名字，第二个参数是分区个数，第三个是 topic 的复制因子个数
        // 当 broker 个数为1个时会创建topic失败，
        // 提示：replication factor: 2 larger than available brokers: 1
        // 只有在集群中才能使用 kafka 的备份功能
        return new NewTopic("topicName", 10, (short) 2);
    }

}

