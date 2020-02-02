package com.example.calculator.service;

import java.util.List;

import com.example.calculator.model.Operation;
import com.example.calculator.model.SciOperation;

public interface ICalculatorService {

	List<Operation> getStandardCalculator(double a, double b);
	List<SciOperation> getScientificCalculator(long a);

}
