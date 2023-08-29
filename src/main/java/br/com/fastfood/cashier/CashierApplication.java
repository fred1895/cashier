package br.com.fastfood.cashier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class CashierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashierApplication.class, args);
	}

}
