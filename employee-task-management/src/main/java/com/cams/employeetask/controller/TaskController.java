package com.cams.employeetask.controller;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cams.employeetask.dto.AssignTaskDTO;
import com.cams.employeetask.dto.TaskRequestDTO;
import com.cams.employeetask.dto.TaskResponseDTO;
import com.cams.employeetask.dto.TaskStatusUpdateDTO;
import com.cams.employeetask.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
	
	private static final Logger log = Logger.getLogger(TaskController.class.getName());
	
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 *  Create a task with by default pending status
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
		log.info("TaskController- Create task from createTask method");
		TaskResponseDTO res = taskService.createTask(dto);
		return ResponseEntity.created(URI.create("/api/v1/tasks/" + res.getId())).body(res);
	}

	/**
	 * Assign task to Employee
	 * @param id
	 * @param dto
	 * @return assigned task object
	 */
	@PutMapping("/{id}/assign")
	public ResponseEntity<TaskResponseDTO> assignTask(@PathVariable Long id, @Valid @RequestBody AssignTaskDTO dto) {
		log.info("TaskController- assign task from assignTask method");
		if (!id.equals(dto.getTaskId())) {
			return ResponseEntity.badRequest().build();
		}
		TaskResponseDTO res = taskService.assignTask(dto.getTaskId(), dto.getEmployeeId());
		return ResponseEntity.ok(res);
	}

	/**
	 *  Update the status
	 * @param id
	 * @param dto
	 * @return updated task object
	 */
	@PatchMapping("/{id}/status")
	public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable Long id,
			@Valid @RequestBody TaskStatusUpdateDTO dto) {
		log.info("TaskController- Task status update from updateStatus method");
		TaskResponseDTO res = taskService.updateTaskStatus(id, dto.getStatus());
		return ResponseEntity.ok(res);
	}
	
	/**
	 * Get all tasks
	 * @return list of tasks
	 */
	@GetMapping
	public ResponseEntity<List<TaskResponseDTO>> getTasks() {
		log.info("TaskController- Get all tasks from getTasks method");
		List<TaskResponseDTO> res = taskService.getAllTasks();
		return ResponseEntity.ok(res);
	}
	
}
