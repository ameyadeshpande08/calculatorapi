package com.example.calculator.model;

import com.example.calculator.service.CalculatorServiceImpl.SciOperationType;

import lombok.Data;

@Data
public class SciOperation {

	private SciOperationType operationName;
	private Object operationValue;
}
