package com.cams.employeetask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cams.employeetask.entity.Task;
import com.cams.employeetask.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByAssignedToId(Long employeeId);

	List<Task> findByAssignedToIdAndStatus(Long employeeId, TaskStatus status);

	List<Task> findByStatus(TaskStatus status);
}
