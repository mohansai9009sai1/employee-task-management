package com.cams.employeetask.service;

import com.cams.employeetask.dto.TaskRequestDTO;
import com.cams.employeetask.dto.TaskResponseDTO;
import com.cams.employeetask.entity.TaskStatus;

import java.util.List;
import java.util.Map;

public interface TaskService {
	TaskResponseDTO createTask(TaskRequestDTO dto);

	TaskResponseDTO assignTask(Long taskId, Long employeeId);

	List<TaskResponseDTO> getTasksForEmployee(Long employeeId, java.util.Optional<TaskStatus> status);
	
	List<TaskResponseDTO> getAllTasks();

	TaskResponseDTO updateTaskStatus(Long taskId, TaskStatus newStatus);

	Map<Long, Long> getCompletedTaskCountPerEmployee();
}
