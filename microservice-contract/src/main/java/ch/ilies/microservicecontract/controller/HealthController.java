package ch.ilies.microservicecontract.controller;

import ch.ilies.microservicecontract.dto.generic.HealthDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthController {

    @GetMapping
    public ResponseEntity<HealthDto> health() {
        return ResponseEntity.ok(new HealthDto(HttpStatus.OK.getReasonPhrase()));
    }
}