package ch.ilies.microserviceclient.dto.client;

import ch.ilies.microserviceclient.dto.api.MicroserviceContractContractDto;
import ch.ilies.microserviceclient.entity.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientWithContractDto extends ClientDto {
    private List<MicroserviceContractContractDto> contracts;

    public ClientWithContractDto(
            final ClientEntity clientEntity,
            final List<MicroserviceContractContractDto> contracts
    ) {
        super(clientEntity);
        this.contracts = contracts;
    }
}