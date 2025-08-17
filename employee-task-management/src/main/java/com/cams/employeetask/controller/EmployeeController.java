package com.cams.employeetask.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cams.employeetask.dto.EmployeeRequestDTO;
import com.cams.employeetask.dto.EmployeeResponseDTO;
import com.cams.employeetask.dto.TaskResponseDTO;
import com.cams.employeetask.entity.TaskStatus;
import com.cams.employeetask.service.EmployeeService;
import com.cams.employeetask.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	private static final Logger log = Logger.getLogger(EmployeeController.class.getName());
	
	private final EmployeeService employeeService;
	private final TaskService taskService;

	public EmployeeController(EmployeeService employeeService, TaskService taskService) {
		this.employeeService = employeeService;
		this.taskService = taskService;
	}
    
	/**
	 * Create Employee record
	 * @param dto
	 * @return created employee
	 */
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
		log.info("EmployeeController - Create Employee from createEmployee method");
		EmployeeResponseDTO res = employeeService.createEmployee(dto);
		return ResponseEntity.created(URI.create("/api/v1/employees/" + res.getId())).body(res);
	}

	/**
	 * 
	 * @return all the Employee records
	 */
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
		log.info("EmployeeController - Get all Employee records from getAll method");
		return ResponseEntity.ok(employeeService.getAll());
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @return Employee records based on status and employee 
	 */
	@GetMapping("/{id}/tasks")
	public ResponseEntity<List<TaskResponseDTO>> getTasks(@PathVariable(value = "id") Long id,@RequestParam(required = false) TaskStatus status) {
		log.info("EmployeeController - Get task records based on employee with status from getTasks method");
		List<TaskResponseDTO> list = taskService.getTasksForEmployee(id, Optional.ofNullable(status));
		return ResponseEntity.ok(list);
	}

	/**
	 * 
	 * @return all the employee completed task count
	 */
	@GetMapping("/stats/completed-tasks")
	public ResponseEntity<Map<Long, Long>> completedStats() {
		log.info("EmployeeController - Find all completed tasks from completedStatus method");
		return ResponseEntity.ok(taskService.getCompletedTaskCountPerEmployee());
	}
}
