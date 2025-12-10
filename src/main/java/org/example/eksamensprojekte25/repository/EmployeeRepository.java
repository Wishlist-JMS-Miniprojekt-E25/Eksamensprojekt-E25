package org.example.eksamensprojekte25.repository;


import org.apache.catalina.User;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        project.setProjectManager(getEmployeeByID(rs.getInt("projectManagerID")));
        project.setProjectName(rs.getString("projectName"));
        project.setProjectDescription(rs.getString("projectDescription"));
        project.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));

        return project;
    };
    private final RowMapper<Timeslot> timeslotRowMapper = (rs, rowNum) -> {
        Timeslot timeslot = new Timeslot();
        timeslot.setTimeslotID(rs.getInt("timeslotID"));
        timeslot.setPlannedDays(rs.getInt("plannedDays"));
        timeslot.setPlannedStartDate(rs.getDate("plannedStartDate"));
        timeslot.setPlannedFinishDate(rs.getDate("plannedFinishDate"));
        timeslot.setActualFinishDate(rs.getDate("actualFinishDate"));
        timeslot.setDifferenceInDays(rs.getInt("differenceInDays"));
        timeslot.setTotalWorkhours(rs.getInt("totalWorkhours"));
        timeslot.setDone(rs.getBoolean("isDone"));
        return timeslot;
    };

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //henter et timeslot baseret på timeslot id
    public Timeslot getTimeslotByID(Integer timeslotID) {
        String sql = """
                SELECT * FROM timeslot
                WHERE timeslotID = ?
                """;
        return jdbcTemplate.queryForObject(sql, timeslotRowMapper, timeslotID);
    }

    //henter alle employees fra databasen
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

    //Henter alle projekter en employee er assignet til
    public List<Project> getProjectsByEmployeeID(Integer employeeID) {
        String sql = """ 
                     SELECT * FROM project p
                     JOIN projectEmployee pe ON p.projectID = pe.projectID 
                     WHERE pe.employeeID = ?
                """;

        return jdbcTemplate.query(sql, projectRowMapper, employeeID);
    }

    //henter alle projekter som en employee er manager for
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

    //tilføjer en employee til databasen
    public Employee addEmployee(String employeeName, String userName, String userPassword) {
        String sql = "INSERT INTO employee (employeeName, userName, userPassword) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employeeName);
            ps.setString(2, userName);
            ps.setString(3, userPassword);
            return ps;
        }, keyHolder);

        int generatedEmployeeID = keyHolder.getKey().intValue();

        return new Employee(generatedEmployeeID, employeeName, userName, userPassword);
    }

    //checker om brugernavnet allerede findes
    public boolean usernameExists(String userName) {
        String sql = "SELECT COUNT(*) FROM employee WHERE userName = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        return count != null && count > 0;
    }

    //fjerner en employee fra databasen
    public void deleteEmployeeByID(Integer employeeID) {
        String sql = "DELETE FROM employee WHERE employeeID = ?";
        jdbcTemplate.update(sql, employeeID);
    }

}