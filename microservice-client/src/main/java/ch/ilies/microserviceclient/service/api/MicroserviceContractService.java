package ch.ilies.microserviceclient.service.api;

import ch.ilies.microserviceclient.dto.api.MicroserviceContractContractDto;
import ch.ilies.microserviceclient.exception.api.MicroserviceContractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MicroserviceContractService {

    private final WebClient webClient;

    @Autowired
    public MicroserviceContractService(@Value("${microservice.contract.url}") final String microserviceContractBaseUri) {
        this.webClient = WebClient.builder()
                .baseUrl(microserviceContractBaseUri)
                .build();
    }

    public List<MicroserviceContractContractDto> getAllContractByClient(final UUID clientId) throws MicroserviceContractException {
        final var url = "/internal/contract/{clientId}";

        log.info("Try to call microservice contract getAllContractByClient API, clientId={}", clientId);
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url).build(clientId))
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorResponse -> {
                    log.warn("Error when calling microservice contract, clientId={}, error={}", clientId, errorResponse);
                    return Mono.error(MicroserviceContractException::new);
                })
                .bodyToMono(new ParameterizedTypeReference<List<MicroserviceContractContractDto>>(){})
                .block();
    }
}
