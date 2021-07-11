package io.santin.kafkamessagechannel.service;

import io.santin.kafkamessagechannel.messaging.MessageDto;
import io.santin.kafkamessagechannel.messaging.SourceSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);
    private final ProducerService producerService;

    public ConsumerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    @StreamListener(SourceSink.INPUT_CHANNEL)
    public void consume(Message<MessageDto> msg) {
        LOGGER.info("Consumed message with payload {} and headers {}", msg.getPayload(), msg.getHeaders());
        producerService.produce(msg.getPayload());
    }
}
