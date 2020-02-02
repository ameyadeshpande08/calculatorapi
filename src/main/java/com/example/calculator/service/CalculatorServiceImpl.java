package com.example.calculator.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.stereotype.Service;

import com.example.calculator.model.Operation;
import com.example.calculator.model.SciOperation;

@Service
public class CalculatorServiceImpl implements ICalculatorService {

	private static DecimalFormat df = new DecimalFormat("0.00");

	public enum OperationType {
		ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION;
	}

	public enum SciOperationType {
		SQUORE, SQUAREROOT, FACTORIAL, PRIMENUMBER;
	}

	@Override
	public List<Operation> getStandardCalculator(double a, double b) {
		List<Operation> operationList = new ArrayList<Operation>();
		for (int i = 0; i < OperationType.values().length; i++) {
			Operation operation = new Operation();
			OperationType operationType = OperationType.values()[i];
			operation.setOperationName(operationType);
			operation.setOperationValue(calculate(operationType, a, b));
			operationList.add(operation);
		}
		return operationList;
	}

	private double calculate(OperationType type, double x, double y) {
		switch (type) {
		case ADDITION:
			return x + y;
		case SUBSTRACTION:
			return x - y;
		case MULTIPLICATION:
			return Math.round(x * y);
		case DIVISION:
			return Double.valueOf(df.format(x / y));
		default:
			throw new AssertionError("Unknown operations " + this);
		}
	}

	@Override
	public List<SciOperation> getScientificCalculator(long a) {
		List<SciOperation> operationList = new ArrayList<SciOperation>();
		for (int i = 0; i < SciOperationType.values().length; i++) {
			SciOperation operation = new SciOperation();
			SciOperationType operationType = SciOperationType.values()[i];
			operation.setOperationName(operationType);
			operation.setOperationValue(calculate(operationType, a));
			operationList.add(operation);
		}
		return operationList;
	}

	private Object calculate(SciOperationType type, long x) {
		switch (type) {
		case SQUORE:
			return Double.valueOf(df.format(x * x));
		case SQUAREROOT:
			return Double.valueOf(df.format(Math.sqrt(x)));
		case FACTORIAL:
			return LongStream.rangeClosed(1, x).reduce(1, (long a, long b) -> a * b);
		case PRIMENUMBER:
			return isPrime(x);
		default:
			throw new AssertionError("Unknown operations " + this);
		}
	}

	public static String isPrime(long number) {
		if (!LongStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0)) {
			return number + " is a prime number ";
		}
		return number + " is not a prime number ";
	}
}
