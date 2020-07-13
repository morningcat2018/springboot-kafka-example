package morning.cat.kafka.client.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/13 3:10 PM
 * @Version 1.0
 */
public class KryoUtils {
    private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);//关闭注册行为
        kryo.setReferences(false);//不支持循环引用
        return kryo;
    });


    public static <T> byte[] serizaber(T t) {
        Kryo kryo = kryos.get();

        try (Output output = new Output(new ByteArrayOutputStream());) {
            kryo.writeObject(output, t);
            return output.getBuffer();
        } finally {
            kryos.remove();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> tClass) {
        Kryo kryo = kryos.get();

        try (Input input = new Input(data);) {
            T object = kryo.readObject(input, tClass);
            return object;
        } finally {
            kryos.remove();
        }
    }
}
