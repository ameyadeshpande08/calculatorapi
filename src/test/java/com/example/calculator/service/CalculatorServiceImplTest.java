package com.example.calculator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.calculator.model.Operation;
import com.example.calculator.model.SciOperation;
import com.example.calculator.service.CalculatorServiceImpl;
import com.example.calculator.service.ICalculatorService;
import com.example.calculator.service.CalculatorServiceImpl.OperationType;
import com.example.calculator.service.CalculatorServiceImpl.SciOperationType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CalculatorServiceImpl.class })
public class CalculatorServiceImplTest {

	@Autowired
	private ICalculatorService calculatorService;

	@BeforeClass
	public static void setupBeforeClass() {
	}

	@AfterClass
	public static void tearDownContext() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStandardCalculator() {
		List<Operation> operationList = calculatorService.getStandardCalculator(3, 3);
		assertFalse(operationList.isEmpty());
		assertEquals(OperationType.ADDITION, operationList.get(0).getOperationName());
		assertEquals(Double.valueOf(6), operationList.get(0).getOperationValue());
		assertEquals(OperationType.SUBSTRACTION, operationList.get(1).getOperationName());
		assertEquals(Double.valueOf(0), operationList.get(1).getOperationValue());
		assertEquals(OperationType.MULTIPLICATION, operationList.get(2).getOperationName());
		assertEquals(Double.valueOf(9), operationList.get(2).getOperationValue());
		assertEquals(OperationType.DIVISION, operationList.get(3).getOperationName());
		assertEquals(Double.valueOf(1), operationList.get(3).getOperationValue());
	}

	@Test
	public void testGetScientificCalculator() {
		List<SciOperation> sciOperationList = calculatorService.getScientificCalculator(3);
		assertFalse(sciOperationList.isEmpty());
		assertEquals(SciOperationType.SQUORE, sciOperationList.get(0).getOperationName());
		assertEquals(Double.valueOf(9), sciOperationList.get(0).getOperationValue());
		assertEquals(SciOperationType.SQUAREROOT, sciOperationList.get(1).getOperationName());
		assertEquals(Double.valueOf(1.73), sciOperationList.get(1).getOperationValue());
		assertEquals(SciOperationType.FACTORIAL, sciOperationList.get(2).getOperationName());
		assertEquals(Long.valueOf(6), sciOperationList.get(2).getOperationValue());
		assertEquals(SciOperationType.PRIMENUMBER, sciOperationList.get(3).getOperationName());
		assertEquals("3 is a prime number ", sciOperationList.get(3).getOperationValue());
	}
}
