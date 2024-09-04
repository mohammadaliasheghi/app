package com.mmad.oauth.util;

import com.mmad.oauth.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = Constant.OAUTH_TOPIC, groupId = Constant.OAUTH_GROUP)
    public void receiveMessage(String message) {
        LOGGER.info("Message Receive {}", message);
    }
}
