package ch.ilies.microserviceclient.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractNameClientIdDto {
    private UUID clientId;

    @JsonProperty("contract_name")
    private String contractName;
}