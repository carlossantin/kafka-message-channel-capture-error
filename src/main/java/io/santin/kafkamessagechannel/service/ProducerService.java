package io.santin.kafkamessagechannel.service;

import io.santin.kafkamessagechannel.messaging.MessageDto;
import io.santin.kafkamessagechannel.messaging.SourceSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.kafka.support.KafkaSendFailureException;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(SourceSink.class)
public class ProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    private final SourceSink sourceSink;

    public ProducerService(SourceSink sourceSink) {
        this.sourceSink = sourceSink;
    }

    public void produce(final MessageDto msg) {
        sourceSink.output().send(MessageBuilder.withPayload(msg).build());
    }

    @ServiceActivator(inputChannel = "errorChannel")
    public void handle(ErrorMessage em) {
        byte[] payload = (byte[])((MessagingException)em.getPayload()).getFailedMessage().getPayload();

        final String topicName = (((KafkaSendFailureException)em.getPayload()).getRecord()).topic();
        final String errorMessage = em.getPayload().getCause().getMessage();

        LOGGER.info("Error sending event {} to topic {}: {}", new String(payload), topicName, errorMessage);
    }
}
