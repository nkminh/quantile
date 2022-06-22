package minh.nguyen.com.example.quantile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
public class QuantileApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantileApplication.class, args);
	}

}
