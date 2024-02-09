package ch.ilies.microservicecontract.repository;

import ch.ilies.microservicecontract.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<ContractEntity, UUID> {}