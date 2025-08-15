package com.vsd.vsd3d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vsd3dApplication {

	public static void main(String[] args) {
		io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.load();
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASS", dotenv.get("DB_PASS"));
		System.setProperty("TELEGRAM_TOKEN", dotenv.get("TELEGRAM_TOKEN"));
		System.setProperty("TELEGRAM_USER", dotenv.get("TELEGRAM_USER"));

		SpringApplication.run(Vsd3dApplication.class, args);

	}

}
