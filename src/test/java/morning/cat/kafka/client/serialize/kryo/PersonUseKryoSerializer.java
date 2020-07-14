package morning.cat.kafka.client.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @describe: {@link Person} 的序列化工具类{@link Kryo}
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:17 AM
 * @Version 1.0
 */
public class PersonUseKryoSerializer implements Serializer<Person> {

    @Override
    public byte[] serialize(String topic, Person data) {
        return KryoUtils.serizaber(data);
    }
}
