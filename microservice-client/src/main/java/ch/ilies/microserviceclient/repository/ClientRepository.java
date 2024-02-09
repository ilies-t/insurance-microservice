package ch.ilies.microserviceclient.repository;

import ch.ilies.microserviceclient.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {}