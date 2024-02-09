package ch.ilies.microserviceclient.dto.client;

import ch.ilies.microserviceclient.entity.ClientEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private UUID id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    private String email;

    public ClientDto(final ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.createdAt = clientEntity.getCreatedAt();
        this.firstName = clientEntity.getFirstName();
        this.lastName = clientEntity.getLastName();
        this.birthDate = clientEntity.getBirthDate();
        this.email = clientEntity.getEmail();
    }
}
