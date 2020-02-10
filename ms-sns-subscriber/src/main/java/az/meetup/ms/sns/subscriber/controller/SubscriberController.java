package az.meetup.ms.sns.subscriber.controller;

import az.meetup.ms.sns.subscriber.model.MessageDto;
import az.meetup.ms.sns.subscriber.service.MessagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sns-subscriber",
        headers = {"Accept=" + MediaType.APPLICATION_JSON_UTF8_VALUE},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
@Api(value = "Controller to test PubSub subscriber via SNS")

public class SubscriberController {

    private final MessagesService messagesService;

    public SubscriberController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/messages")
    @ApiOperation("Get received messages")
    public ResponseEntity<List<MessageDto>> getMessages() {
        return ResponseEntity.ok(messagesService.getMessages());
    }
}
