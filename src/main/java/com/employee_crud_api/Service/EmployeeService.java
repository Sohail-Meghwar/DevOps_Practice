package com.employee_crud_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee_crud_api.Dtos.EmployeeRequestDTO;
import com.employee_crud_api.Dtos.EmployeeResponseDTO;
import com.employee_crud_api.Entity.Employee;
import com.employee_crud_api.Repo.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToResponseDTO(savedEmployee);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        return convertToResponseDTO(employee);
    }

    public EmployeeResponseDTO updateEmployee(
            Long id,
            EmployeeRequestDTO request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return convertToResponseDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }

    private EmployeeResponseDTO convertToResponseDTO(Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }


}
