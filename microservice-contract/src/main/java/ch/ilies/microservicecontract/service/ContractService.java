package ch.ilies.microservicecontract.service;

import ch.ilies.microservicecontract.component.MessageProducer;
import ch.ilies.microservicecontract.dto.contract.ContractDto;
import ch.ilies.microservicecontract.dto.message.ContractNameClientIdDto;
import ch.ilies.microservicecontract.entity.ClientContractEntity;
import ch.ilies.microservicecontract.entity.ContractEntity;
import ch.ilies.microservicecontract.exception.ContractNotFoundException;
import ch.ilies.microservicecontract.repository.ClientContractRepository;
import ch.ilies.microservicecontract.repository.ContractRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientContractRepository clientContractRepository;
    private final MessageProducer messageProducer;


    @Autowired
    public ContractService(
            final ContractRepository contractRepository,
            final ClientContractRepository clientContractRepository,
            final MessageProducer messageProducer
    ) {
        this.contractRepository = contractRepository;
        this.clientContractRepository = clientContractRepository;
        this.messageProducer = messageProducer;
    }

    public List<ContractDto> getAllByClient(final UUID id) {
        final List<ClientContractEntity> contracts = this.clientContractRepository.findAllByClientId(id);

        return contracts.stream()
                .map(clientContract -> new ContractDto(clientContract.getContract()))
                .toList();
    }

    public List<ContractDto> getAll() {
        final List<ContractEntity> clients = this.contractRepository.findAll();

        return clients.stream()
                .map(ContractDto::new)
                .toList();
    }

    public void subscribeClient(final UUID contractId, final UUID clientId) {
        this.contractRepository.findById(contractId)
                .ifPresentOrElse(
                    contract -> this.handleSubscribeClient(contract, contractId, clientId),
                    () -> {
                        log.error("Contract not found during subscription, contractId={}, clientId={}", contractId, clientId);
                        throw new ContractNotFoundException();
                    }
                );


    }

    private void handleSubscribeClient(
            final ContractEntity contract,
            final UUID contractId,
            final UUID clientId
    ) {
        final ClientContractEntity clientContract = new ClientContractEntity();
        clientContract.setContract(contract);
        clientContract.setClientId(clientId);
        this.clientContractRepository.save(clientContract);
        log.info("New client contract has been successfully saved, contractId={}, clientId={}", contractId, clientId);

        final ContractNameClientIdDto contractNameClientIdDto = ContractNameClientIdDto.builder()
                .contractName(contract.getName())
                .clientId(clientId)
                .build();

        this.messageProducer.sendMessageToMsClient(contractNameClientIdDto);
    }
}
