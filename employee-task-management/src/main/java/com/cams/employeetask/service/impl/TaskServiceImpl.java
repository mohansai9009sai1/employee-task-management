package com.cams.employeetask.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cams.employeetask.dto.TaskRequestDTO;
import com.cams.employeetask.dto.TaskResponseDTO;
import com.cams.employeetask.entity.Employee;
import com.cams.employeetask.entity.Task;
import com.cams.employeetask.entity.TaskStatus;
import com.cams.employeetask.exception.ResourceNotFoundException;
import com.cams.employeetask.repository.TaskRepository;
import com.cams.employeetask.service.EmployeeService;
import com.cams.employeetask.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	private final EmployeeService employeeService;

	public TaskServiceImpl(TaskRepository taskRepository, EmployeeService employeeService) {
		this.taskRepository = taskRepository;
		this.employeeService = employeeService;
	}

	@Override
	public TaskResponseDTO createTask(TaskRequestDTO dto) {
		Task t = new Task(dto.getTitle(), dto.getDescription());
		t = taskRepository.save(t);
		return toDto(t);
	}

	@Override
	public TaskResponseDTO assignTask(Long taskId, Long employeeId) {
		Task t = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found: " + taskId));
		Employee e = employeeService.findByIdOrThrow(employeeId);
		t.setAssignedTo(e);
		t = taskRepository.save(t);
		return toDto(t);
	}

	@Override
	public List<TaskResponseDTO> getTasksForEmployee(Long employeeId, Optional<TaskStatus> status) {
		employeeService.findByIdOrThrow(employeeId);
		List<Task> tasks = status.map(s -> taskRepository.findByAssignedToIdAndStatus(employeeId, s))
				.orElseGet(() -> taskRepository.findByAssignedToId(employeeId));
		return tasks.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	@Override
	public List<TaskResponseDTO> getAllTasks() {
		
		List<Task> tasks = taskRepository.findAll();
		
		return tasks.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public TaskResponseDTO updateTaskStatus(Long taskId, TaskStatus newStatus) {
		Task t = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found: " + taskId));
		t.setStatus(newStatus);
		t = taskRepository.save(t);
		return toDto(t);
	}

	@Override
	public Map<Long, Long> getCompletedTaskCountPerEmployee() {
		return taskRepository.findAll().stream().filter(t -> t.getAssignedTo() != null)
				.filter(t -> t.getStatus() == TaskStatus.COMPLETED)
				.collect(Collectors.groupingBy(t -> t.getAssignedTo().getId(), Collectors.counting()));
	}

	private TaskResponseDTO toDto(Task t) {
		Long assignedId = t.getAssignedTo() != null ? t.getAssignedTo().getId() : null;
		String assignedName = t.getAssignedTo() != null ? t.getAssignedTo().getName() : null;
		return new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), assignedId,
				assignedName);
	}
}
