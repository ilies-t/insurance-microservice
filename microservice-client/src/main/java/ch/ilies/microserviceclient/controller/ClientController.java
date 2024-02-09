package ch.ilies.microserviceclient.controller;

import ch.ilies.microserviceclient.dto.client.ClientDto;
import ch.ilies.microserviceclient.dto.client.ClientWithContractDto;
import ch.ilies.microserviceclient.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        log.info("Handling getAll");
        return ResponseEntity.ok(this.clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientWithContractDto> getOne(@PathVariable UUID id) {
        log.info("Handling getOne, id={}", id);
        return ResponseEntity.ok(this.clientService.getOne(id));
    }
}