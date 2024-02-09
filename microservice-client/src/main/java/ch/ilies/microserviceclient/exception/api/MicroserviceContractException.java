package ch.ilies.microserviceclient.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MicroserviceContractException extends RuntimeException {}