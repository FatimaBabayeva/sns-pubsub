package az.meetup.ms.sns.subscriber.service;

import az.meetup.ms.sns.subscriber.mapper.MessagesMapper;
import az.meetup.ms.sns.subscriber.dao.MessagesRepository;
import az.meetup.ms.sns.subscriber.dao.model.MessageEntity;
import az.meetup.ms.sns.subscriber.model.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public Long saveMessage(MessageDto dto) {
        MessageEntity entity = messagesRepository.save(MessagesMapper.INSTANCE.dtoToEntity(dto));
        return entity.getId();
    }

    public List<MessageDto> getMessages() {
        List<MessageEntity> entities = messagesRepository.findAll();
        return entities.stream()
                .map(MessagesMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}

