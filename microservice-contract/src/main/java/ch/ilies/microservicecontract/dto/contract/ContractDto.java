package ch.ilies.microservicecontract.dto.contract;

import ch.ilies.microservicecontract.entity.ContractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private UUID id;
    private String name;

    public ContractDto(final ContractEntity contractEntity) {
        this.id = contractEntity.getId();
        this.name = contractEntity.getName();
    }
}