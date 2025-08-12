package com.cams.employeetask.dto;

import com.cams.employeetask.entity.TaskStatus;

public class TaskResponseDTO {
	private Long id;
	private String title;
	private String description;
	private TaskStatus status;
	private Long assignedToId;
	private String assignedToName;

	public TaskResponseDTO() {
	}

	public TaskResponseDTO(Long id, String title, String description, TaskStatus status, Long assignedToId,
			String assignedToName) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.assignedToId = assignedToId;
		this.assignedToName = assignedToName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public com.cams.employeetask.entity.TaskStatus getStatus() {
		return status;
	}

	public void setStatus(com.cams.employeetask.entity.TaskStatus status) {
		this.status = status;
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(Long assignedToId) {
		this.assignedToId = assignedToId;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
}
