package online.grisk.zeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class KronosApplication {

	public static void main(String[] args) {
		SpringApplication.run(KronosApplication.class, args);
	}

}