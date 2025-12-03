package org.example.eksamensprojekte25.repository;


import org.apache.catalina.User;
import org.example.eksamensprojekte25.model.Employee;
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
        employee.setManager(rs.getBoolean("isManager"));
        return employee;

    };

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee getEmployeeByID(Integer employeeID) {
        String sql = """
                SELECT * FROM employee
                WHERE employeeID = ?
                """;
        return jdbcTemplate.queryForObject(sql, employeeRowMapper, employeeID);
    }

    public Employee findEmployeeByCredentials(String userName, String userPassword){
        String sql = """
                SELECT *
                FROM employee
                WHERE userName = ? AND userPassword = ?
                """;
        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper, userName, userPassword);
        return employees.isEmpty() ? null : employees.get(0);
    }
}

