package morning.cat.kafka.client.serialize;

import morning.cat.kafka.client.domain.Person;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @describe: TODO 类描述信息
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
