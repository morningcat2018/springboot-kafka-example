package morning.cat.kafka.client.serialize;

import morning.cat.kafka.client.domain.Person;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/13 2:14 PM
 * @Version 1.0
 */
public class PerssonSerializerTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.setId(333L);
        person.setPersonNo("P00046JJ99");
        person.setName("zhangsan");
        System.out.println(person);

        PersonUseKryoSerializer personUseKryoSerializer = new PersonUseKryoSerializer();
        byte[] bytes = personUseKryoSerializer.serialize("", person);
        //System.out.println(bytes);

        PersonUseKryoDeserializer personUseKryoDeserializer = new PersonUseKryoDeserializer();
        Person obj = personUseKryoDeserializer.deserialize("",bytes);
        System.out.println(obj);
    }
}
