package ch.ilies.microservicecontract.controller;


import ch.ilies.microservicecontract.dto.contract.ContractDto;
import ch.ilies.microservicecontract.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/internal/contract")
public class InternalContractController {

    private final ContractService contractService;

    public InternalContractController(final ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<List<ContractDto>> getAllByClient(@PathVariable("client_id") UUID id) {
        log.info("Handling getAllByClient, id={}", id);
        return ResponseEntity.ok(this.contractService.getAllByClient(id));
    }
}
