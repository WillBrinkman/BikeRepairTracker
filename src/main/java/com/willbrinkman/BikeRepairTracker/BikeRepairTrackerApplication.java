package com.willbrinkman.BikeRepairTracker;

import com.willbrinkman.BikeRepairTracker.controllers.HomeController;
import com.willbrinkman.BikeRepairTracker.models.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BikeRepairTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeRepairTrackerApplication.class, args);
	}

}
