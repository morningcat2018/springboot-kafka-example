package morning.cat.kafka.domain;

import lombok.*;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/19 8:31 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Message {
    private Integer id;
    private String msg;
}
