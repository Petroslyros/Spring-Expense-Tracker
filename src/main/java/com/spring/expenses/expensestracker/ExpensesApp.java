package com.spring.expenses.expensestracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enable automatic @CreatedDate and @LastModifiedDate handling
public class ExpensesApp {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesApp.class, args);
	}

}
