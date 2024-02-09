package ch.ilies.microservicecontract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MicroserviceContractApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceContractApplication.class, args);
	}

}
