package morning.cat.kafka.client.serialize;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @describe: {@link Person} 的自定义序列化实现
 * @author: morningcat.zhang
 * @date: 2020/7/14 11:47 AM
 * @Version 1.0
 */
public class PersonSerializer implements Serializer<Person> {
    @Override
    public byte[] serialize(String topic, Person data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putLong(data.getId());
        byteBuffer.putInt(data.getPersonNo().length());
        byteBuffer.put(data.getPersonNo().getBytes());

        byte[] bytes = data.getName().getBytes(Charset.forName("UTF-8"));
        byteBuffer.putInt(bytes.length);
        byteBuffer.put(bytes);
        return byteBuffer.array();
    }
}
