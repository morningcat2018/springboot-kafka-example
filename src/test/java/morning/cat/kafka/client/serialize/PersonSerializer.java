package morning.cat.kafka.client.serialize;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @describe: 自定义序列化实现
 * @author: morningcat.zhang
 * @date: 2020/7/14 11:47 AM
 * @Version 1.0
 */
public class PersonSerializer implements Serializer<Person> {
    @Override
    public byte[] serialize(String topic, Person data) {
        return new byte[0];
    }
}
