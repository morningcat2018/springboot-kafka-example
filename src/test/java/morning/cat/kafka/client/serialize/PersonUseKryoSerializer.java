package morning.cat.kafka.client.serialize;

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

    private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);//关闭注册行为
        kryo.setReferences(false);//支持循环引用
        return kryo;
    });

    @Override
    public byte[] serialize(String topic, Person data) {
        return KryoUtils.serizaber(data);
    }
}
