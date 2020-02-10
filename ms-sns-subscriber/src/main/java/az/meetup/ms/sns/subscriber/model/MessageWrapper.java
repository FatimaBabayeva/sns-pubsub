package az.meetup.ms.sns.subscriber.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageWrapper {
    @JsonProperty("MessageId")
    private String messageId;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("TopicArn")
    private String topicArn;
}
