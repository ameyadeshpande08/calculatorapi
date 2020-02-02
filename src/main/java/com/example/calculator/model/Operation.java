package com.example.calculator.model;

import com.example.calculator.service.CalculatorServiceImpl.OperationType;

import lombok.Data;

@Data
public class Operation {
	private OperationType operationName;
	private Double operationValue;
}
