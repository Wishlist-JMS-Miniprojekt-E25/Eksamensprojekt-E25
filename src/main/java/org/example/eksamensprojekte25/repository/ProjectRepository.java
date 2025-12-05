package org.example.eksamensprojekte25.repository;


import org.example.eksamensprojekte25.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
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

    //henter de employees, som er på samme task
    public List<Employee> getEmployeesByTaskID(Integer taskID) {
        String sql = """
                    SELECT * FROM employee e
                    JOIN taskemployee te ON e.employeeID = te.employeeID
                    WHERE te.taskID = ?
                """;

        return jdbcTemplate.query(sql, employeeRowMapper, taskID);
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
        return jdbcTemplate.queryForObject(sql, projectRowMapper, projectID);
    }

    //henter et projekt baseret på task id
    public Project getProjectByTaskID(Integer taskID) {
        String sql = """
                SELECT * FROM project p
                JOIN task t ON p.projectID = t.projectID
                WHERE taskID = ?
                """;
        return jdbcTemplate.queryForObject(sql, projectRowMapper, taskID);
    }

    //henter en task baseret på task id
    public Task getTaskByID(Integer taskID) {
        String sql = """
                SELECT * FROM task
                WHERE taskID = ?
                """;
        return jdbcTemplate.queryForObject(sql, taskRowMapper, taskID);
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

    //Inserter employees til junction table, når man vælger dem under oprettelse af projekt
    public void assignEmployeesToProject(Integer projectID, List<Integer> employeeIDs) {
        String sql = "INSERT INTO projectEmployee (employeeID, projectID) VALUES (?, ?)";

        for (Integer employeeID : employeeIDs) {
            jdbcTemplate.update(sql, employeeID, projectID);
        }
    }

    public Timeslot createTimeslot(int plannedDays, Date plannedStartDate, Date plannedFinishDate) {
        String sql = "INSERT INTO timeslot (plannedDays, plannedStartDate, plannedFinishDate) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, plannedDays);
            ps.setDate(2, plannedStartDate);
            ps.setDate(3, plannedFinishDate);
            return ps;
        }, keyHolder);

        int timeslotID = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        String timeslotSql = """
                SELECT * FROM timeslot
                WHERE timeslotID = ?
                """;
        return jdbcTemplate.queryForObject(timeslotSql, timeslotRowMapper, timeslotID);
    }

    public Project addProject(Integer projectManagerID, String projectName, String projectDescription, Integer timeslotID) {
        String sql = "INSERT INTO project (projectManagerID, projectName, projectDescription, timeslotID) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, projectManagerID);
            ps.setString(2, projectName);
            ps.setString(3, projectDescription);
            ps.setInt(4, timeslotID);
            return ps;
        }, keyHolder);


        int projectID = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;

        return new Project(projectID, projectManagerID, projectName, projectDescription, timeslotID);
    }

    public Task addTask(String taskName, String taskDescription, Integer timeslotID, Integer projectID) {

        String sql = """
                INSERT INTO task (taskName, taskDescription, timeSlotID, projectID)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, taskName);
            ps.setString(2, taskDescription);
            ps.setInt(3, timeslotID);
            ps.setInt(4, projectID);
            return ps;
        }, keyHolder);

        int taskID = keyHolder.getKey().intValue();

        return new Task(taskID, taskName, taskDescription, timeslotID, null, projectID);
    }

    public void assignEmployeesToTask(Integer taskID, List<Integer> employeeIDs) {
        String sql = "INSERT INTO taskEmployee (employeeID, taskID) VALUES (?, ?)";

        for (Integer empID : employeeIDs) {
            jdbcTemplate.update(sql, empID, taskID);
        }
    }


    public void deleteProjectByID(Integer projectID) {
        String sql = "DELETE FROM project WHERE projectID = ?";
        jdbcTemplate.update(sql, projectID);
    }

    //henter alle subtasks med samme task id
    public int countSubtasksByTaskID(Integer taskID) {
        String sql = """
                SELECT COUNT(*) FROM subtask
                WHERE taskID = ?
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class,taskID);
    }

    public Subtask addSubtask (String subtaskName, String subtaskDescription, Integer timeslotID, Integer taskID, Integer employeeID){
        String sql = "INSERT INTO subtask (subtaskName, subtaskDescription, timeslotID, taskID, employeeID) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subtaskName);
            ps.setString(2, subtaskDescription);
            ps.setInt(3, timeslotID);
            ps.setInt(4, taskID);
            ps.setInt(5, employeeID);
            return ps;
        }, keyHolder);

        int subtaskID = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;

        return new Subtask(subtaskID, subtaskName, subtaskDescription, timeslotID, taskID, employeeID);
    }

    public void deleteTaskByID(Integer taskID) {
        String sql = "DELETE FROM task WHERE taskID = ?";
        jdbcTemplate.update(sql, taskID);
    }
}
