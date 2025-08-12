package com.cams.employeetask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cams.employeetask.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
		ErrorResponseDTO err = new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found",
				ex.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponseDTO> handleDuplicate(DuplicateResourceException ex, HttpServletRequest req) {
		ErrorResponseDTO err = new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Conflict",
				ex.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex,
			HttpServletRequest req) {
		String msg = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage()).reduce((a, b) -> a + "; " + b)
				.orElse(ex.getMessage());
		ErrorResponseDTO err = new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Validation Error", msg, req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest req) {
		ErrorResponseDTO err = new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Server Error", ex.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
