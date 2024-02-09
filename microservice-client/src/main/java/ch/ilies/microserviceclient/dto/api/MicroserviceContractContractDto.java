package ch.ilies.microserviceclient.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MicroserviceContractContractDto {
    private UUID id;
    private String name;
}
