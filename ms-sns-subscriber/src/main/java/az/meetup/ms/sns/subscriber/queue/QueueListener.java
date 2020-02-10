package az.meetup.ms.sns.subscriber.queue;

import az.meetup.ms.sns.subscriber.model.MessageWrapper;
import az.meetup.ms.sns.subscriber.service.MessagesService;
import az.meetup.ms.sns.subscriber.model.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueListener {
    private final ObjectMapper objectMapper;
    private final MessagesService messagesService;

    public QueueListener(ObjectMapper objectMapper, MessagesService messagesService) {
        this.objectMapper = objectMapper;
        this.messagesService = messagesService;
    }

    private static Logger logger = LogManager.getLogger(QueueListener.class);

    @JmsListener(destination = "${aws.sqs.queue}")
    public void receiveSnsMessage(String queueMessage) throws IOException {
        try {
            MessageWrapper response = objectMapper.readValue(queueMessage, MessageWrapper.class);
            MessageDto message = objectMapper.readValue(response.getMessage(), MessageDto.class);
            messagesService.saveMessage(message);
        } catch (IOException ex) {
            logger.error("Can't parse message: {}", queueMessage);
            throw ex;
        } catch (Exception ex) {
            logger.error("Receive sns message throws exception", ex);
            throw ex;
        }
    }
}
