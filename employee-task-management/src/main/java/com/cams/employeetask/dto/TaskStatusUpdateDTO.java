package com.cams.employeetask.dto;

import com.cams.employeetask.entity.TaskStatus;

import jakarta.validation.constraints.NotNull;

public class TaskStatusUpdateDTO {
	@NotNull
	private TaskStatus status;

	public TaskStatusUpdateDTO() {
	}

	public TaskStatusUpdateDTO(TaskStatus status) {
		this.status = status;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
