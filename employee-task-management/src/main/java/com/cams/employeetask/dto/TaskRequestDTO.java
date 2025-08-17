package com.cams.employeetask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequestDTO {
	@NotBlank
	@Size(max = 200)
	private String title;
	@Size(max = 2000)
	private String description;

	public TaskRequestDTO() {
	}

	public TaskRequestDTO(String title, String description) {
		this.title = title;
		this.description = description;
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

	
}
