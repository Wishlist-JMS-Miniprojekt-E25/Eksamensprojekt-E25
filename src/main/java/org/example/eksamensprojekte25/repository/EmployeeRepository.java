package org.example.eksamensprojekte25.repository;


import org.apache.catalina.User;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt("employeeID"));
        employee.setEmployeeName(rs.getString("employeeName"));
        employee.setUserName(rs.getString("userName"));
        employee.setUserPassword(rs.getString("userPassword"));
        return employee;

    };
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setProjectID(rs.getInt("projectID"));
        project.setProjectManagerID(rs.getInt("projectManagerID"));
        project.setProjectName(rs.getString("projectName"));
        project.setProjectDescription(rs.getString("projectDescription"));
        project.setTimeslotID(rs.getInt("timeSlotID"));

        return project;
    };

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    //henter en employee baseret på employee id
    public Employee getEmployeeByID(Integer employeeID) {
        String sql = """
                SELECT * FROM employee
                WHERE employeeID = ?
                """;
        return jdbcTemplate.queryForObject(sql, employeeRowMapper, employeeID);
    }

    //Henter alle projekter baseret op employee id
    public List<Project> getProjectsByEmployeeID(Integer employeeID) {
        String sql = """ 
                     SELECT * FROM project p
                     JOIN projectEmployee pe ON p.projectID = pe.projectID 
                     WHERE pe.employeeID = ?
                """;

        return jdbcTemplate.query(sql, projectRowMapper, employeeID);
    }

    //henter alle projekter baseret på manager id
    public List<Project> getProjectsByManagerID(Integer managerID) {
        String sql = """ 
                     SELECT * FROM project p
                     WHERE p.projectManagerID = ?
                """;

        return jdbcTemplate.query(sql, projectRowMapper, managerID);
    }

    //henter en employee baseret på employee userName og userPassword
    public Employee findEmployeeByCredentials(String userName, String userPassword) {
        String sql = """
                SELECT *
                FROM employee
                WHERE userName = ? AND userPassword = ?
                """;
        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper, userName, userPassword);
        return employees.isEmpty() ? null : employees.get(0);
    }
}

