package com.ttr2.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ttr2.model.Employee;
import com.ttr2.model.Order;
import com.ttr2.model.Status;

@Configuration
public class LoadDatabase {

	@Bean
	public CommandLineRunner initDatabase(EmployeeRepository repository, 
			OrderRepository orderRepository) {
		return args -> {
			repository.save(new Employee("Bilbo Baggins", "burglar"));
			repository.save(new Employee("Frodo Baggins", "thief"));
			
			orderRepository.save(new Order("McHammer", Status.COMPLETED));
			orderRepository.save(new Order("iDont", Status.IN_PROGRESS));
		};
	}
}
