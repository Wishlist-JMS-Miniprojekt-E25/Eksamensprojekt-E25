package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class EmployeeRepositoryIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;
}