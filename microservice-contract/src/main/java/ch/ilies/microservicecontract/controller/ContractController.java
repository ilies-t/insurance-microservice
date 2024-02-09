package ch.ilies.microservicecontract.controller;

import ch.ilies.microservicecontract.dto.contract.ContractDto;
import ch.ilies.microservicecontract.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(final ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll() {
        log.info("Handling getAll");
        return ResponseEntity.ok(this.contractService.getAll());
    }

    @PostMapping("/{contract_id}/{client_id}")
    public ResponseEntity<Void> subscribeClient(
            @PathVariable("contract_id") UUID contractId,
            @PathVariable("client_id") UUID clientId
    ) {
        log.info("Handling subscribeClient, contractId={}, clientId={}", contractId, clientId);
        this.contractService.subscribeClient(contractId, clientId);
        return ResponseEntity.noContent().build();
    }
}