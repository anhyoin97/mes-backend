package com.example.demo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(err -> err.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
	}
	
	static class ErrorResponse {
		public List<String> errors;
		
		public ErrorResponse(List<String> errors) {
			this.errors = errors;
		}
		
		public List<String> getErrors() {
			return errors;
		}
	}
}
