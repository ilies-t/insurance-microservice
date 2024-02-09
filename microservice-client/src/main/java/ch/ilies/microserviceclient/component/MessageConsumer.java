package ch.ilies.microserviceclient.component;

import ch.ilies.microserviceclient.dto.message.ContractNameClientIdDto;
import ch.ilies.microserviceclient.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    private final ClientService clientService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageConsumer(final ClientService clientService) {
        this.clientService = clientService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "${kafka.ms-client.topic-name}", groupId = "${kafka.ms-client.consumer-group-id}")
    public void listen(final String message) {
        log.info("Received Kafka message, message={}", message);
        try {
            final ContractNameClientIdDto contractNameClientIdDto = this.objectMapper.readValue(message, ContractNameClientIdDto.class);
            this.clientService.handleClientEmail(contractNameClientIdDto);
        } catch (JsonProcessingException e) {
            log.error("Cannot deserialize Kafka message, content={}", message);
        }
    }
}