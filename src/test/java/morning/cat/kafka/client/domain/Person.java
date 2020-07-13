package morning.cat.kafka.client.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/13 11:15 AM
 * @Version 1.0
 */
@Data
public class Person implements Serializable {

    private Long id;

    private String personNo;

    private String name;

//    private LocalDate birthday;
}
