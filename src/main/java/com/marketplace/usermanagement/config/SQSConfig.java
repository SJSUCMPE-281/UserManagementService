package com.marketplace.usermanagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class SQSConfig {
    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory(MessageConverter messageConverter) {

        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        factory.setArgumentResolvers(singletonList(new PayloadArgumentResolver(messageConverter)));
        return factory;
    }

    @Bean
    protected MessageConverter messageConverter(ObjectMapper objectMapper) {

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        // Serialization support:
        converter.setSerializedPayloadClass(String.class);
        // Deserialization support: (suppress "contentType=application/json" header requirement)
        converter.setStrictContentTypeMatch(false);
        return converter;

    }
}
