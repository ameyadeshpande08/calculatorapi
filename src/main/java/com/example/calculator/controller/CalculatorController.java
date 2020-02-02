package com.example.calculator.controller;

import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculator.model.Operation;
import com.example.calculator.model.SciOperation;
import com.example.calculator.service.ICalculatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/calculator-service/1.0")
public class CalculatorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

	@Autowired
	ICalculatorService calculatorService;

	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(notes = "This operation takes the input and gives the Json with Standard Calculator ", value = "", tags = "Standard")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 422, message = "Unprocessable entity Functional error"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	@GetMapping("/getStdCalculation")
	public String getStdCalculator(@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam("second") String second) {
		LOGGER.info("Received request to fetch the details for first Element:: {}", first);
		LOGGER.info("Received request to fetch the details for second Element:: {}", second);
		double x;
		double y;
		try {
			x = Double.valueOf(first);
			y = Double.valueOf(second);
			List<Operation> obj = calculatorService.getStandardCalculator(x, y);
			JSONArray jsonObj = new JSONArray(obj);
			return jsonObj.toString();
		} catch (Exception e) {
			// Output expected NumberFormatException.
			LOGGER.error("String is not a digit:{}", e);
		}
		return null;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(notes = "This operation takes the input and gives the Json with Scientific Calculator ", value = "", tags = "Scientific")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 422, message = "Unprocessable entity Functional error"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	@GetMapping("/getSciCalculation")
	public String getScientificCalculator(@RequestParam(value = "first", defaultValue = "0") String first) {
		LOGGER.info("Received request to fetch the details for first Element:: {}", first);
		long x;
		try {
			x = Long.valueOf(first);
			List<SciOperation> obj = calculatorService.getScientificCalculator(x);
			JSONArray jsonObj = new JSONArray(obj);
			return jsonObj.toString();
		} catch (Exception e) {
			// Output expected NumberFormatException.
			LOGGER.error("String is not a digit:{}", e);
		}
		return null;
	}
}
