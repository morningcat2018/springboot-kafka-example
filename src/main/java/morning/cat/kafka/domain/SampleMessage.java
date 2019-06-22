package morning.cat.kafka.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleMessage {

    private final Long id;

    private final String message;

    @JsonCreator
    public SampleMessage(@JsonProperty("id") Long id, @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "SampleMessage{id=" + this.id + ", message='" + this.message + "'}";
    }

}
