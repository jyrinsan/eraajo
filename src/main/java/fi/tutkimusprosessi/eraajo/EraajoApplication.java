package fi.tutkimusprosessi.eraajo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EraajoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EraajoApplication.class, args);
	}

}
