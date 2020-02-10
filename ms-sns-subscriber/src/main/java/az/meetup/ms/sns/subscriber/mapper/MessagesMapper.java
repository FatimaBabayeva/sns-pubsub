package az.meetup.ms.sns.subscriber.mapper;

import az.meetup.ms.sns.subscriber.dao.model.MessageEntity;
import az.meetup.ms.sns.subscriber.model.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class MessagesMapper {

    public static final MessagesMapper INSTANCE = Mappers.getMapper(MessagesMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "message", source = "message")
    })
    public abstract MessageEntity dtoToEntity(MessageDto dto);

    @Mappings({
            @Mapping(target = "message", source = "message")
    })
    public abstract MessageDto entityToDto(MessageEntity entity);
}
