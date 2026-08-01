package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeRequest;
import com.example.employeeservice.dto.EmployeeResponse;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = new Employee(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.department(),
                request.role(),
                request.salary()
        );
        return EmployeeResponse.from(repository.save(employee));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
        employee.setRole(request.role());
        employee.setSalary(request.salary());
        return EmployeeResponse.from(repository.save(employee));
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public List<EmployeeResponse> getEmployees() {
        return repository.findAll().stream()
                .map(EmployeeResponse::from)
                .toList();
    }
}
