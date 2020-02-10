package az.meetup.ms.sns.subscriber.dao;

import az.meetup.ms.sns.subscriber.dao.model.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepository extends CrudRepository<MessageEntity, Long> {
    List<MessageEntity> findAll();
}
