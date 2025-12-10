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
import java.util.List;

@Repository
public class ProjectRepository {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setProjectID(rs.getInt("projectID"));
        project.setProjectManager(getEmployeeByID(rs.getInt("projectManagerID")));
        project.setProjectName(rs.getString("projectName"));
        project.setProjectDescription(rs.getString("projectDescription"));
        project.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));

        return project;
    };
    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setTaskID(rs.getInt("taskID"));
        task.setTaskName(rs.getString("taskName"));
        task.setTaskDescription(rs.getString("taskDescription"));
        task.setProject(getProjectByID(rs.getInt("projectID")));
        task.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));

        return task;
    };
    private final RowMapper<Subtask> subtaskRowMapper = (rs, rowNum) -> {
        Subtask subtask = new Subtask();
        subtask.setSubtaskID(rs.getInt("subtaskID"));
        subtask.setSubtaskName(rs.getString("subtaskName"));
        subtask.setSubtaskDescription(rs.getString("subtaskDescription"));
        subtask.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));
        subtask.setTask(getTaskByID(rs.getInt("taskID")));
        subtask.setAssignedEmployee(getEmployeeByID(rs.getInt("employeeID")));

        return subtask;
    };
    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt("employeeID"));
        employee.setEmployeeName(rs.getString("employeeName"));
        employee.setUserName(rs.getString("userName"));
        employee.setUserPassword(rs.getString("userPassword"));
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
    private final RowMapper<Project> archivedProjectRowMapper = (rs, rowNum) -> {
        Project archivedproject = new Project();
        archivedproject.setProjectID(rs.getInt("projectID"));
        archivedproject.setProjectManager(getEmployeeByID(rs.getInt("projectManagerID")));
        archivedproject.setProjectName(rs.getString("projectName"));
        archivedproject.setProjectDescription(rs.getString("projectDescription"));
        archivedproject.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));

        return archivedproject;
    };
    private final RowMapper<Task> archivedTaskRowMapper = (rs, rowNum) -> {
        Task archivedtask = new Task();
        archivedtask.setTaskID(rs.getInt("taskID"));
        archivedtask.setTaskName(rs.getString("taskName"));
        archivedtask.setTaskDescription(rs.getString("taskDescription"));
        archivedtask.setProject(getProjectByID(rs.getInt("projectID")));
        archivedtask.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));

        return archivedtask;
    };
    private final RowMapper<Subtask> archivedSubtaskRowMapper = (rs, rowNum) -> {
        Subtask archivedsubtask = new Subtask();
        archivedsubtask.setSubtaskID(rs.getInt("subtaskID"));
        archivedsubtask.setSubtaskName(rs.getString("subtaskName"));
        archivedsubtask.setSubtaskDescription(rs.getString("subtaskDescription"));
        archivedsubtask.setTimeslot(getTimeslotByID(rs.getInt("timeSlotID")));
        archivedsubtask.setTask(getTaskByID(rs.getInt("taskID")));
        archivedsubtask.setAssignedEmployee(getEmployeeByID(rs.getInt("employeeID")));

        return archivedsubtask;
    };

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
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

    //tilføjer et nyt timeslot i databasen og henter det igen,
    //for at returnere et timeslot objekt til skabelse af project, task og subtaks.
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

    //henter en employee baseret på employee id
    public Employee getEmployeeByID(Integer employeeID) {
        String sql = """
                SELECT * FROM employee
                WHERE employeeID = ?
                """;
        return jdbcTemplate.queryForObject(sql, employeeRowMapper, employeeID);
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

    //Inserter employees til junction table, når man vælger dem under oprettelse af projekt
    public void assignEmployeesToProject(Integer projectID, List<Integer> employeeIDs) {
        String sql = "INSERT INTO projectEmployee (employeeID, projectID) VALUES (?, ?)";
        for (Integer employeeID : employeeIDs) {
            jdbcTemplate.update(sql, employeeID, projectID);
        }
    }

    //inserter employees til juntion tabel, når man vælger dem under oprettelse af task
    public void assignEmployeesToTask(Integer taskID, List<Integer> employeeIDs) {
        String sql = "INSERT INTO taskEmployee (employeeID, taskID) VALUES (?, ?)";

        for (Integer empID : employeeIDs) {
            jdbcTemplate.update(sql, empID, taskID);
        }
    }

    //henter et projekt baseret på projekt id
    public Project getProjectByID(Integer projectID) {
        String sql = """
                SELECT * FROM project
                WHERE projectID = ?
                """;
        return jdbcTemplate.queryForObject(sql, projectRowMapper, projectID);
    }

    public List<Project> getArchivedProjects(Integer managerID) {
        String sql = """
                SELECT * FROM archivedProject
                WHERE projectManagerID = ?
                """;
        return jdbcTemplate.query(sql,archivedProjectRowMapper, managerID);
    }

    //tilføjet et projekt til databasen
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
        return new Project(projectID, null, projectName, projectDescription, null);
    }

    //fjerner et projekt fra databasen
    public void deleteProjectByID(Integer projectID) {
        String sql = "DELETE FROM project WHERE projectID = ?";
        jdbcTemplate.update(sql, projectID);
    }

    //henter en task baseret på task id
    public Task getTaskByID(Integer taskID) {
        String sql = """
                SELECT * FROM task
                WHERE taskID = ?
                """;
        return jdbcTemplate.queryForObject(sql, taskRowMapper, taskID);
    }

    //henter alle tasks baseret på et projekt id
    public List<Task> getTasksByProjectID(Integer projectID) {
        String sql = """
                SELECT * FROM task
                WHERE projectID = ?
                """;
        return jdbcTemplate.query(sql, taskRowMapper, projectID);
    }

    //tilføjer en task til databasen
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

        return new Task(taskID, taskName, taskDescription, null, null, null);
    }

    //fjerner en task fra databasen
    public void deleteTaskByID(Integer taskID) {
        String sql = "DELETE FROM task WHERE taskID = ?";
        jdbcTemplate.update(sql, taskID);
    }

    //henter en subtask baseret på subtask id
    public Subtask getSubtaskByID(Integer subtaskID) {
        String sql = """
                SELECT * FROM subtask
                WHERE subtaskID = ?
                """;
        return jdbcTemplate.queryForObject(sql, subtaskRowMapper, subtaskID);
    }

    //henter alle subtasks baseret på et task id
    public List<Subtask> getSubtasksByTaskID(Integer taskID) {
        String sql = """
                SELECT * FROM subtask
                WHERE taskID = ?
                """;
        return jdbcTemplate.query(sql, subtaskRowMapper, taskID);
    }

    //tilføjer en subtask til databasen
    public Subtask addSubtask(String subtaskName, String subtaskDescription, Integer timeslotID, Integer taskID, Integer employeeID) {
        String sql = "INSERT INTO subtask (subtaskName, subtaskDescription, timeslotID, taskID, employeeID) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subtaskName);
            ps.setString(2, subtaskDescription);
            ps.setInt(3, timeslotID);
            ps.setInt(4, taskID);
            ps.setInt(5, employeeID);
            return ps;
        }, keyHolder);

        int subtaskID = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;

        return new Subtask(subtaskID, subtaskName, subtaskDescription, null, null, null);
    }

    //fjerner en subtask fra databasen
    public void deleteSubtaskByID(Integer subtaskID) {
        String sql = "DELETE FROM subtask WHERE subtaskID = ?";
        jdbcTemplate.update(sql, subtaskID);
    }

    //Fjerner en employee fra projekt under editProject, hvis man vælger at un-check en employee
    public void removeEmployeeFromProject(Integer projectID, Integer employeeID) {
        String sql = "DELETE FROM projectEmployee WHERE projectID = ? AND employeeID = ?";

        jdbcTemplate.update(sql, projectID, employeeID);
    }

    //Opdaterer planned start date og planned finish date for et projekt, hvis man vælger at ændre det i editProject
    public void editTimeslot(Integer timeslotID, Integer plannedDays, Date plannedStartDate, Date plannedFinishDate) {
        String sql = """
                UPDATE timeslot
                SET plannedDays = ?,
                plannedStartDate = ?,
                plannedFinishDate = ?
                WHERE timeslotID = ?
                """;
        jdbcTemplate.update(sql, plannedDays, plannedStartDate, plannedFinishDate, timeslotID);
    }

    //Opdaterer navn, beskrivelse og timeslot
    public void editProject(Project project, Integer projectID) {
        String sql = """
                UPDATE project
                SET projectName = ?,
                projectDescription = ?,
                timeslotID = ?
                WHERE projectID = ?
                """;
        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(), project.getTimeslot().getTimeslotID(), projectID);
    }

    public void finalizeTimeslot(Timeslot timeslot, Integer timeslotID) {
        String sql = """
                UPDATE timeslot
                SET isDone = ?,
                totalWorkhours = ?,
                actualFinishDate = ?,
                differenceInDays = ?
                WHERE timeslotID = ?
                """;

        jdbcTemplate.update(sql, timeslot.isDone(), timeslot.getTotalWorkhours(), timeslot.getActualFinishDate(), timeslot.getDifferenceInDays(), timeslotID);
    }

    public void updateTotalWorkhoursForTimeslot(Timeslot timeslot, Integer timeslotID) {
        String sql = "UPDATE timeslot SET totalWorkhours = ? WHERE timeslotID = ?";

        jdbcTemplate.update(sql, timeslot.getTotalWorkhours(), timeslotID);
    }

    public void archiveSubtask(Subtask subtask) {

        String sql = """
                INSERT INTO archivedSubtask
                (subtaskID,
                subtaskName,
                subtaskDescription,
                timeslotID,
                taskID,
                employeeID) VALUES (?, ?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, subtask.getSubtaskID());
            ps.setString(2, subtask.getSubtaskName());
            ps.setString(3, subtask.getSubtaskDescription());
            ps.setInt(4, subtask.getTimeslot().getTimeslotID());
            ps.setInt(5, subtask.getTask().getTaskID());
            ps.setInt(6, subtask.getAssignedEmployee().getEmployeeID());
            return ps;
        }, keyHolder);
    }

    public void archiveTask(Task task) {
        String sql = """
                INSERT INTO archivedTask
                (taskID,
                taskName,
                taskDescription,
                timeslotID,
                projectID) VALUES (?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, task.getTaskID());
            ps.setString(2, task.getTaskName());
            ps.setString(3, task.getTaskDescription());
            ps.setInt(4, task.getTimeslot().getTimeslotID());
            ps.setInt(5, task.getProject().getProjectID());
            return ps;
        }, keyHolder);
    }

    public void archiveProject(Project project) {
        String sql = """
                INSERT INTO archivedProject
                (projectID,
                projectManagerID,
                projectName,
                projectDescription,
                timeslotID) VALUES (?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, project.getProjectID());
            ps.setInt(2, project.getProjectManager().getEmployeeID());
            ps.setString(3, project.getProjectName());
            ps.setString(4, project.getProjectDescription());
            ps.setInt(5, project.getTimeslot().getTimeslotID());
            return ps;
        }, keyHolder);
    }
}