package com.cams.employeetask.service;

import com.cams.employeetask.dto.EmployeeRequestDTO;
import com.cams.employeetask.dto.EmployeeResponseDTO;
import com.cams.employeetask.entity.Employee;

import java.util.List;

public interface EmployeeService {
	EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

	Employee findByIdOrThrow(Long id);

	List<EmployeeResponseDTO> getAll();
}
