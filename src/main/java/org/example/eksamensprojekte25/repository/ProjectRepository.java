package org.example.eksamensprojekte25.repository;


import org.example.eksamensprojekte25.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setProjectID(rs.getInt("projectID"));
        project.setProjectManagerID(rs.getInt("projectManagerID"));
        project.setProjectName(rs.getString("projectName"));
        project.setProjectDescription(rs.getString("projectDescription"));
        project.setTimeslotID(rs.getInt("timeSlotID"));

        return project;
    };
    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setTaskID(rs.getInt("taskID"));
        task.setTaskName(rs.getString("taskName"));
        task.setTaskDescription(rs.getString("taskDescription"));
        task.setProjectID(rs.getInt("projectID"));
        task.setTimeslotID(rs.getInt("timeSlotID"));

        return task;
    };
    private final RowMapper<Subtask> subtaskRowMapper = (rs, rowNum) -> {
        Subtask subtask = new Subtask();
        subtask.setSubtaskID(rs.getInt("subtaskID"));
        subtask.setSubtaskName(rs.getString("subtaskName"));
        subtask.setSubtaskDescription(rs.getString("subtaskDescription"));
        subtask.setTaskID(rs.getInt("taskID"));
        subtask.setEmployeeID(rs.getInt("employeeID"));
        subtask.setTimeslotID(rs.getInt("timeSlotID"));

        return subtask;
    };
    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt("employeeID"));
        employee.setEmployeeName(rs.getString("employeeName"));
        employee.setUserName(rs.getString("userName"));
        employee.setUserPassword(rs.getString("userPassword"));
        employee.setManager(rs.getBoolean("isManager"));
        return employee;
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

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //henter alle timeslots
    public List<Timeslot> getAllTimeslots() {
        String sql = """
                SELECT * FROM timeslot
                """;
        return jdbcTemplate.query(sql, timeslotRowMapper);
    }

    //henter de employees, som er på samme projekt
    public List<Employee> getEmployeesByProjectID(Integer projectID) {
        String sql = """
                    SELECT * FROM employee e
                    JOIN projectEmployee pe ON e.employeeID = pe.employeeID
                    WHERE pe.projectID = ?
                """;

        return jdbcTemplate.query(sql, employeeRowMapper, projectID);
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

    //henter et projekt baseret på projekt id
    public Project getProjectByID(Integer projectID) {
        String sql = """
                SELECT * FROM project
                WHERE projectID = ?
                """;
        return jdbcTemplate.queryForObject(sql, projectRowMapper,projectID);
    }

    //Henter alle tasks for et bestemt projekt
    public List<Task> getTasksByProjectID(Integer projectID) {
        String sql = """
                    SELECT * FROM task 
                    WHERE projectID = ?
                """;

        return jdbcTemplate.query(sql, taskRowMapper, projectID);
    }

    //Henter alle subtasks for en bestemt task
    public List<Subtask> getSubtasksByTaskID(Integer taskID) {
        String sql = """
                    SELECT * FROM subtask
                    WHERE taskID = ?
                """;

        return jdbcTemplate.query(sql, subtaskRowMapper, taskID);
    }
}
