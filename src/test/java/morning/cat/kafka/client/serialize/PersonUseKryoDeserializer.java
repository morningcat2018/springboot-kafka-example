package morning.cat.kafka.client.serialize;

import com.esotericsoftware.kryo.Kryo;
import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @describe: {@link Person} 的反序列化工具类{@link Kryo}
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:21 AM
 * @Version 1.0
 */
public class PersonUseKryoDeserializer implements Deserializer<Person> {


    @Override
    public Person deserialize(String topic, byte[] data) {
        return KryoUtils.deserialize(data, Person.class);
    }
}
