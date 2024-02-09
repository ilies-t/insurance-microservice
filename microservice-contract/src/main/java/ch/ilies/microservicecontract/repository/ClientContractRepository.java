package ch.ilies.microservicecontract.repository;

import ch.ilies.microservicecontract.entity.ClientContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientContractRepository extends JpaRepository<ClientContractEntity, UUID> {
    List<ClientContractEntity> findAllByClientId(final UUID clientId);
}