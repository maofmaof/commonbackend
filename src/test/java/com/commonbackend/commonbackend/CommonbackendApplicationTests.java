package com.commonbackend.commonbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import com.commonbackend.commonbackend.controller.OrderController;
import com.commonbackend.commonbackend.model.Order;

@SpringBootTest
class CommonbackendApplicationTests {

	@Autowired
	OrderController orderController;

	@Autowired
	TestRestTemplate restTemplate;


	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
	}
    
	/*
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:8080/orders",
				Order.class)).contains("Hello, World");
	}

	 */












}


