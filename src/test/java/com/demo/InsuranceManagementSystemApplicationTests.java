package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.insurance.userservice.InsuranceManagementSystemApplication.class,
                 properties = "spring.autoconfigure.exclude=org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration")
class InsuranceManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
