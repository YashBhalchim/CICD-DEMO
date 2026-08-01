package com.example.employeeservice;

import com.example.employeeservice.dto.EmployeeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndListEmployees() {
        EmployeeRequest request = new EmployeeRequest(
                "Jane",
                "Doe",
                "jane@example.com",
                "Engineering",
                "Developer",
                75000.0
        );

        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/api/employees", request, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> listResponse = restTemplate.getForEntity("/api/employees", String.class);
        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody()).contains("Jane");
    }
}
