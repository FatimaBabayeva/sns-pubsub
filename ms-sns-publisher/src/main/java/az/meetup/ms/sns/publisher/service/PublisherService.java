package az.meetup.ms.sns.publisher.service;

import az.meetup.ms.sns.publisher.model.MessageDto;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private static final String TEST_SNS_TOPIC = "test-sns-topic";
    private final AmazonSNS snsClient;
    private final ObjectMapper objectMapper;
    private static Logger logger = LogManager.getLogger(PublisherService.class);

    public PublisherService(AmazonSNS snsClient, ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.objectMapper = objectMapper;
    }

    public void publishViaSns(MessageDto dto) {

        try {

            String messageString = objectMapper.writeValueAsString(dto);

            CreateTopicResult topicCreationResult = snsClient.createTopic(TEST_SNS_TOPIC);
            PublishRequest request = new PublishRequest()
                    .withTopicArn(topicCreationResult.getTopicArn())
                    .withMessage(messageString);
            snsClient.publish(request);

        } catch (Exception e) {
            logger.error("Publish message via Sns throws exception", e);
        }
    }
}
