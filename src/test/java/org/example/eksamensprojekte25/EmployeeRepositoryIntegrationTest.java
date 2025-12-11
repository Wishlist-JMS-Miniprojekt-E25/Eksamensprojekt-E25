package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class EmployeeRepositoryIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void testFindEmployeeByValidCredentials() {
        //bruger findes i test DB. metode returnerer et Employee objekt
        String username = "Sim";
        String password = "123";

        Employee employee = employeeRepository.findEmployeeByCredentials(username, password);

        assertNotNull(employee, "Employee should be found with valid credentials");
        assertEquals(username, employee.getUserName());
        assertEquals(password, employee.getUserPassword());
        assertEquals(1, employee.getEmployeeID()); // Simon har employeeID = 1
        assertEquals("Simon", employee.getEmployeeName());
    }

    @Test
    void testFindEmployeeByInvalidCredentials() {
        //bruger findes ikke. metode returnerer null
        String username = "NonExistent";
        String password = "wrong";

        Employee employee = employeeRepository.findEmployeeByCredentials(username, password);

        assertNull(employee, "Employee should be null with invalid credentials");
    }
}