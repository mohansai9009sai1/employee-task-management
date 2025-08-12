package com.cams.employeetask.controller;

import com.cams.employeetask.dto.EmployeeRequestDTO;
import com.cams.employeetask.dto.EmployeeResponseDTO;
import com.cams.employeetask.dto.TaskResponseDTO;
import com.cams.employeetask.entity.TaskStatus;
import com.cams.employeetask.service.EmployeeService;
import com.cams.employeetask.service.TaskService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	private final EmployeeService employeeService;
	private final TaskService taskService;

	public EmployeeController(EmployeeService employeeService, TaskService taskService) {
		this.employeeService = employeeService;
		this.taskService = taskService;
	}

	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
		EmployeeResponseDTO res = employeeService.createEmployee(dto);
		return ResponseEntity.created(URI.create("/api/v1/employees/" + res.getId())).body(res);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
		return ResponseEntity.ok(employeeService.getAll());
	}

	@GetMapping("/{id}/tasks")
	public ResponseEntity<List<TaskResponseDTO>> getTasks(@PathVariable Long id,
			@RequestParam(required = false) TaskStatus status) {
		List<TaskResponseDTO> list = taskService.getTasksForEmployee(id, Optional.ofNullable(status));
		return ResponseEntity.ok(list);
	}

	@GetMapping("/stats/completed-tasks")
	public ResponseEntity<Map<Long, Long>> completedStats() {
		return ResponseEntity.ok(taskService.getCompletedTaskCountPerEmployee());
	}
}
