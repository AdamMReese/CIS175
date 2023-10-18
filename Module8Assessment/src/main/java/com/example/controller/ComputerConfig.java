package com.example.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.computerdb.Computer;

@Configuration
public class ComputerConfig {

	@Bean
	Computer computer() {
		return new Computer(null, null, null, false);
	}
}
