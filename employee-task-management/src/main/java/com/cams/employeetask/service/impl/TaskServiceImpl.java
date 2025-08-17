package com.cams.employeetask.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
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
	
	private static final Logger log = Logger.getLogger(TaskServiceImpl.class.getName());
	
	private final TaskRepository taskRepository;
	private final EmployeeService employeeService;

	public TaskServiceImpl(TaskRepository taskRepository, EmployeeService employeeService) {
		this.taskRepository = taskRepository;
		this.employeeService = employeeService;
	}

	@Override
	public TaskResponseDTO createTask(TaskRequestDTO dto) {
		log.info("TaskService- Create task from createTask method");
		Task t = new Task(dto.getTitle(), dto.getDescription());
		t = taskRepository.save(t);
		return toDto(t);
	}

	@Override
	public TaskResponseDTO assignTask(Long taskId, Long employeeId) {
		log.info("TaskService- assign task from assignTask method");
		Task t = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found: " + taskId));
		Employee e = employeeService.findByIdOrThrow(employeeId);
		t.setAssignedTo(e);
		t = taskRepository.save(t);
		return toDto(t);
	}

	@Override
	public List<TaskResponseDTO> getTasksForEmployee(Long employeeId, Optional<TaskStatus> status) {
		log.info("TaskService- Get all tasks based on status and employee from getTasksForEmployee method");
		employeeService.findByIdOrThrow(employeeId);
		List<Task> tasks = status.map(s -> taskRepository.findByAssignedToIdAndStatus(employeeId, s))
				.orElseGet(() -> taskRepository.findByAssignedToId(employeeId));
		return tasks.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	@Override
	public List<TaskResponseDTO> getAllTasks() {
		log.info("TaskService- Get all tasks from getAllTasks method");
		List<Task> tasks = taskRepository.findAll();
		
		return tasks.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public TaskResponseDTO updateTaskStatus(Long taskId, TaskStatus newStatus) {
		log.info("TaskService- Task status update from updateTaskStatus method");
		Task t = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found: " + taskId));
		t.setStatus(newStatus);
		t = taskRepository.save(t);
		return toDto(t);
	}

	@Override
	public Map<Long, Long> getCompletedTaskCountPerEmployee() {
		log.info("TaskService- Get all completed tasks from getCompletedTaskCountPerEmployee method");
		return taskRepository.findAll().stream().filter(t -> t.getAssignedTo() != null)
				.filter(t -> t.getStatus() == TaskStatus.COMPLETED)
				.collect(Collectors.groupingBy(t -> t.getAssignedTo().getId(), Collectors.counting()));
	}

	private TaskResponseDTO toDto(Task t) {
		log.info("TaskService- convert entity to dto from toDto method");
		Long assignedId = t.getAssignedTo() != null ? t.getAssignedTo().getId() : null;
		String assignedName = t.getAssignedTo() != null ? t.getAssignedTo().getName() : null;
		return new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), assignedId,
				assignedName);
	}
}
