package com.jrios83.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {
	private CalculatorApplication calculator = new CalculatorApplication();
	@Test
	public void testSum() {
		assertEquals(6, calculator.sum(3, 3));
	}
	/*void contextLoads() {
	}*/

}
