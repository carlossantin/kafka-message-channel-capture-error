package io.santin.kafkamessagechannel.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface SourceSink {

    @Input(INPUT_CHANNEL)
    SubscribableChannel input();

    @Output(OUTPUT_CHANNEL)
    MessageChannel output();

    String INPUT_CHANNEL = "input";
    String OUTPUT_CHANNEL = "output";
}
