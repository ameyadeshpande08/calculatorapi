package com.example.calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testStdCalculation() throws Exception {
		this.mockMvc.perform(get("/calculator-service/1.0/getStdCalculation").param("first", "23").param("second", "23"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].operationValue", is(46)))
				.andExpect(jsonPath("$[0].operationName", is("ADDITION")))
				.andExpect(jsonPath("$[1].operationValue", is(0)))
				.andExpect(jsonPath("$[1].operationName", is("SUBSTRACTION")))
				.andExpect(jsonPath("$[2].operationValue", is(529)))
				.andExpect(jsonPath("$[2].operationName", is("MULTIPLICATION")))
				.andExpect(jsonPath("$[3].operationValue", is(1)))
				.andExpect(jsonPath("$[3].operationName", is("DIVISION")));
	}

	@Test(expected = Exception.class)
	public void testStdCalculationWithException() throws Exception {
		this.mockMvc.perform(get("/calculator-service/1.0/getStdCalculation").param("first", "23ss").param("second", "23"))
				.andDo(print()).andExpect(status().isOk()).andExpect(null);
	}

	@Test
	public void testScientificCalculation() throws Exception {
		this.mockMvc.perform(get("/calculator-service/1.0/getSciCalculation").param("first", "3")).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].operationValue", is(9)))
				.andExpect(jsonPath("$[0].operationName", is("SQUORE")))
				.andExpect(jsonPath("$[1].operationValue", is(1.73)))
				.andExpect(jsonPath("$[1].operationName", is("SQUAREROOT")))
				.andExpect(jsonPath("$[2].operationValue", is(6)))
				.andExpect(jsonPath("$[2].operationName", is("FACTORIAL")))
				.andExpect(jsonPath("$[3].operationValue", is("3 is a prime number ")))
				.andExpect(jsonPath("$[3].operationName", is("PRIMENUMBER")));
	}

	@Test(expected = Exception.class)
	public void testScientificCalculationWithException() throws Exception {
		this.mockMvc.perform(get("/calculator-service/1.0/getSciCalculation").param("first", "3ss")).andDo(print())
				.andExpect(status().isOk()).andExpect(null);
	}
}
