package ch.ilies.microservicecontract.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "client_contract")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID clientId;

    @ManyToOne(fetch = FetchType.EAGER)
    private ContractEntity contract;
}