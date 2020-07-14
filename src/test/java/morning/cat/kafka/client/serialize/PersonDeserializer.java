package morning.cat.kafka.client.serialize;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @describe: {@link Person} 的自定义反序列化实现
 * @author: morningcat.zhang
 * @date: 2020/7/14 1:57 PM
 * @Version 1.0
 */
public class PersonDeserializer implements Deserializer<Person> {
    @Override
    public Person deserialize(String topic, byte[] data) {
        Person person = new Person();
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
        byteBuffer.put(data);
        byteBuffer.flip();
        person.setId(byteBuffer.getLong());

        int l1 = byteBuffer.getInt();
        byte[] personNoBytes = new byte[l1];
        byteBuffer.get(personNoBytes, 0, l1);
        person.setPersonNo(new String(personNoBytes, 0, l1));

        int l2 = byteBuffer.getInt();
        byte[] nameBytes = new byte[l2];
        byteBuffer.get(nameBytes, 0, l2);
        person.setName(new String(nameBytes, 0, l2, Charset.forName("UTF-8")));

        return person;
    }
}
