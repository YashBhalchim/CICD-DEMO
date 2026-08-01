package com.example.employeeservice;

import com.example.employeeservice.dto.EmployeeRequest;
import com.example.employeeservice.dto.EmployeeResponse;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Alice", "Smith", "alice@example.com", "HR", "Manager", 90000.0);
        employee.setId(1L);
    }

    @Test
    void shouldCreateEmployee() {
        EmployeeRequest request = new EmployeeRequest("Alice", "Smith", "alice@example.com", "HR", "Manager", 90000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse response = employeeService.createEmployee(request);

        assertThat(response.firstName()).isEqualTo("Alice");
        assertThat(response.email()).isEqualTo("alice@example.com");
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldUpdateEmployee() {
        EmployeeRequest request = new EmployeeRequest("Alice", "Brown", "alice@example.com", "HR", "Manager", 95000.0);
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse response = employeeService.updateEmployee(1L, request);

        assertThat(response.lastName()).isEqualTo("Brown");
        assertThat(response.salary()).isEqualTo(95000.0);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldDeleteEmployee() {
        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void shouldGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<EmployeeResponse> responses = employeeService.getEmployees();

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).firstName()).isEqualTo("Alice");
        verify(employeeRepository).findAll();
    }
}
