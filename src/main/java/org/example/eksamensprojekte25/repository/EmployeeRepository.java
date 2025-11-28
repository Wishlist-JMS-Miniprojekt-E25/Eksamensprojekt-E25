package org.example.eksamensprojekte25.repository;


import org.apache.catalina.User;
import org.example.eksamensprojekte25.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

}