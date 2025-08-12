package com.cams.employeetask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequestDTO {
	@NotBlank
	@Size(max = 100)
	private String name;
	@NotBlank
	@Email
	private String email;
	@Size(max = 100)
	private String department;

	public EmployeeRequestDTO() {
	}

	public EmployeeRequestDTO(String name, String email, String department) {
		this.name = name;
		this.email = email;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
