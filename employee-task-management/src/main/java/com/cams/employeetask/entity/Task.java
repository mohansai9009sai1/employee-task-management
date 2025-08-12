package com.cams.employeetask.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column(length = 2000)
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.PENDING;
	
	@ManyToOne
	@JoinColumn(name = "assigned_to_id")
	private Employee assignedTo;

	public Task() {
	}

	public Task(String title, String description) {
		this.title = title;
		this.description = description;
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Employee getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}
}
