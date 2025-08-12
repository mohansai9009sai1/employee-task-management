package com.cams.employeetask.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cams.employeetask.dto.EmployeeRequestDTO;
import com.cams.employeetask.dto.EmployeeResponseDTO;
import com.cams.employeetask.entity.Employee;
import com.cams.employeetask.exception.DuplicateResourceException;
import com.cams.employeetask.exception.ResourceNotFoundException;
import com.cams.employeetask.repository.EmployeeRepository;
import com.cams.employeetask.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
		employeeRepository.findByEmail(dto.getEmail()).ifPresent(e -> {
			throw new DuplicateResourceException("Email already exists");
		});
		Employee emp = new Employee(dto.getName(), dto.getEmail(), dto.getDepartment());
		emp = employeeRepository.save(emp);
		return new EmployeeResponseDTO(emp.getId(), emp.getName(), emp.getEmail(), emp.getDepartment(), null);
	}

	@Override
	public Employee findByIdOrThrow(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
	}

	@Override
	public List<EmployeeResponseDTO> getAll() {
		return employeeRepository.findAll().stream().map(e -> new EmployeeResponseDTO(e.getId(), e.getName(),
				e.getEmail(), e.getDepartment(), (long) e.getTasks().size())).collect(Collectors.toList());
	}
}
