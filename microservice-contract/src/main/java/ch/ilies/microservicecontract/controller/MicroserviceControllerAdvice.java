package ch.ilies.microservicecontract.controller;

import ch.ilies.microservicecontract.dto.generic.ErrorDto;
import ch.ilies.microservicecontract.exception.ContractNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MicroserviceControllerAdvice {

    @ExceptionHandler(ContractNotFoundException.class)
    public ResponseEntity<ErrorDto> handleClientNotFound() {
        return new ResponseEntity<>(ErrorDto.notFound(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleExceptions(final Exception e) {
        log.error("Unknown error happened", e);
        return new ResponseEntity<>(ErrorDto.internalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}