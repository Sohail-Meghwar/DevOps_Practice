package com.employee_crud_api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_crud_api.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
