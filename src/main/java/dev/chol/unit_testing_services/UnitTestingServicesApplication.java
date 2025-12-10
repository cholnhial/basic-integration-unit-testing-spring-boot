package dev.chol.unit_testing_services;

import dev.chol.unit_testing_services.domain.Order;
import dev.chol.unit_testing_services.repository.OrderRepository;
import dev.chol.unit_testing_services.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnitTestingServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitTestingServicesApplication.class, args);
	}
}
