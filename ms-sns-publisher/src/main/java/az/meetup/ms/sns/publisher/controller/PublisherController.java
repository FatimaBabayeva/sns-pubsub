package az.meetup.ms.sns.publisher.controller;

import az.meetup.ms.sns.publisher.model.MessageDto;
import az.meetup.ms.sns.publisher.service.PublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sns-publisher",
        headers = {"Accept=" + MediaType.APPLICATION_JSON_UTF8_VALUE},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Controller to test PubSub publisher via SNS")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @PostMapping("/publish")
    @ApiOperation("Method to test message publishing via AWS SNS")
    public ResponseEntity testSNSPublishing(@RequestBody MessageDto request) {
        service.publishViaSns(request);
        return ResponseEntity.ok().build();
    }
}
