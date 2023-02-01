package com.commonbackend.commonbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.commonbackend.commonbackend.controller.OrderController;
import com.commonbackend.commonbackend.controller.ProductController;

@SpringBootTest
class CommonbackendApplicationTests {

	@Autowired 
	OrderController orderController;
	@Autowired
	ProductController productController;

	//private TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
		assertThat(productController).isNotNull();
	}
    
	/*
	 * public void testthingy(){
	 * testRestTemplate.exchange("http://localhost:8080/api", HttpMethod.GET, null, 
	 * new ParameterizedTypeReference<Object>())
	 * }
	 */

}
