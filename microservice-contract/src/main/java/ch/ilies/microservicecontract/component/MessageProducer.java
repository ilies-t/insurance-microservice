package ch.ilies.microservicecontract.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String msClientTopicName;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageProducer(
            final KafkaTemplate<String, String> kafkaTemplate,
            @Value("${kafka.ms-client.topic-name}") final String msClientTopicName
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.msClientTopicName = msClientTopicName;
        this.objectMapper = new ObjectMapper();
    }

    public void sendMessageToMsClient(final Object message) {
        try {
            final String jsonMessage = this.objectMapper.writeValueAsString(message);
            this.kafkaTemplate.send(this.msClientTopicName, jsonMessage);
            log.info("Message has been successfully sent into ms-client topic, jsonMessage={}", jsonMessage);
        } catch (JsonProcessingException e) {
            log.error("Cannot serialize Object as JSON string", e);
        }
    }
}
