package ch.ilies.microserviceclient.service;

import ch.ilies.microserviceclient.dto.api.MicroserviceContractContractDto;
import ch.ilies.microserviceclient.dto.client.ClientDto;
import ch.ilies.microserviceclient.dto.client.ClientWithContractDto;
import ch.ilies.microserviceclient.dto.message.ContractNameClientIdDto;
import ch.ilies.microserviceclient.entity.ClientEntity;
import ch.ilies.microserviceclient.exception.api.MicroserviceContractException;
import ch.ilies.microserviceclient.repository.ClientRepository;
import ch.ilies.microserviceclient.exception.ClientNotFoundException;
import ch.ilies.microserviceclient.service.api.MicroserviceContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ClientService {

    private final MicroserviceContractService microserviceContractService;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(
            final MicroserviceContractService microserviceContractService,
            final ClientRepository clientRepository
    ) {
        this.microserviceContractService = microserviceContractService;
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getAll() {
        final List<ClientEntity> clients = this.clientRepository.findAll();

        return clients.stream()
                .map(ClientDto::new)
                .toList();
    }

    public ClientWithContractDto getOne(final UUID id) {
        final Optional<ClientEntity> clientEntity = this.clientRepository.findById(id);
        return clientEntity
                .map(this::getOneClientWithContractDto)
                .orElseThrow(ClientNotFoundException::new);
    }

    public void handleClientEmail(final ContractNameClientIdDto contractNameClientIdDto) {
        final UUID clientId = contractNameClientIdDto.getClientId();

        this.clientRepository.findById(contractNameClientIdDto.getClientId()).ifPresentOrElse(
                client -> {
                    log.info(
                            "Will send email to client about new subscription, id={}, firstName={}, email={}, contractName={}",
                            clientId, client.getFirstName(), client.getEmail(), contractNameClientIdDto.getContractName()
                    );
                    // ...
                    // send email here
                    // ...
                },
                () -> log.error("Client not found, id={}", clientId)
        );
    }

    private ClientWithContractDto getOneClientWithContractDto(final ClientEntity clientEntity) {

        // find clients contract using microservice contract internal API
        final UUID clientId = clientEntity.getId();
        List<MicroserviceContractContractDto> contractDtoList = null;

        try {
            contractDtoList = this.microserviceContractService
                    .getAllContractByClient(clientId);
            log.info("All client contract has been successfully retrieved, clientId={}, size={}", clientId, contractDtoList.size());
        } catch (MicroserviceContractException microserviceContractException) {
            log.warn("Microservice contract API cannot be contacted, clientId={}", clientId);
        }

        return new ClientWithContractDto(clientEntity, contractDtoList);
    }
}
