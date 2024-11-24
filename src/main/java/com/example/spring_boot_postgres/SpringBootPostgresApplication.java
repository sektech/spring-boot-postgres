package com.example.spring_boot_postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootPostgresApplication  implements CommandLineRunner {

	@Autowired
	public DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPostgresApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(dataSource.getConnection().toString());


	}
}
