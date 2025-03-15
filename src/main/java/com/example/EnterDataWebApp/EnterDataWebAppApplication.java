package com.example.EnterDataWebApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EnterDataWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterDataWebAppApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
		return args -> {
			if (args.length < 2) {
				System.out.println("Usage: java -jar webapp.jar <username> <password>");
				return;
			}

			String username = args[0];
			String password = args[1];

			AuthServiceUser user = new AuthServiceUser(username, password);
			String authServiceUrl = "http://auth-service:8081/auth";

			try {
				String response = restTemplate.postForObject(authServiceUrl, user, String.class);
				System.out.println("Authentication Response: " + response);

				if ("yes".equals(response)) {
					System.out.print("Enter your name: ");
					String name = System.console().readLine();

					System.out.print("Enter your average: ");
					double average = Double.parseDouble(System.console().readLine());

					System.out.println("User authenticated. Name: " + name + ", Average: " + average);
				} else {
					System.out.println("Authentication failed. Cannot proceed.");
				}

			} catch (Exception e) {
				System.err.println("Error during authentication: " + e.getMessage());
			}
		};
	}

}
