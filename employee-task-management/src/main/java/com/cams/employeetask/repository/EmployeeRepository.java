package com.cams.employeetask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cams.employeetask.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);
}
