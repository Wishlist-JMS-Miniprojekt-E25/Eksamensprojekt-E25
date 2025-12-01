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

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Henter alle projekter og deres timeslots
    public List<Project> getProjects(Integer employeeID) {
        String sql = """
                    SELECT
                        p.projectID,
                        p.projectName,
                        p.projectDescription,
                        p.projectManagerID,
                        t.timeSlotID,
                        t.plannedDays,
                        t.plannedStartDate,
                        t.plannedFinishDate,
                        t.actualFinishDate,
                        t.differenceInDays,
                        t.totalWorkhours,
                        t.isDone
                    FROM project p
                    JOIN timeSlot t ON p.timeSlotID = t.timeSlotID
                    WHERE p.projectManagerID = ?
                """;

        return jdbcTemplate.query(sql, projectRowMapper, employeeID);
    }

    //Henter alle tasks og deres timeslots
    public List<Task> getTasks(Integer projectID) {
        String sql = """
                    SELECT
                        t.taskID,
                        t.taskName,
                        t.taskDescription,
                        t.projectID,
                        ts.timeSlotID,
                        ts.plannedDays,
                        ts.plannedStartDate,
                        ts.plannedFinishDate,
                        ts.actualFinishDate,
                        ts.differenceInDays,
                        ts.totalWorkhours,
                        ts.isDone
                    FROM task t
                    JOIN timeSlot ts ON t.timeSlotID = ts.timeSlotID
                    WHERE t.projectID = ?
                """;

        return jdbcTemplate.query(sql, taskRowMapper, projectID);
    }

    //Henter alle subtasks og deres timeslots
    public List<Subtask> getSubtasks(Integer taskID) {
        String sql = """
                    SELECT
                        st.subtaskID,
                        st.subtaskName,
                        st.subtaskDescription,
                        st.taskID,
                        st.employeeID,
                        ts.timeSlotID,
                        ts.plannedDays,
                        ts.plannedStartDate,
                        ts.plannedFinishDate,
                        ts.actualFinishDate,
                        ts.differenceInDays,
                        ts.totalWorkhours,
                        ts.isDone
                    FROM subtask st
                    JOIN timeSlot ts ON st.timeSlotID = ts.timeSlotID
                    WHERE st.taskID = ?
                """;

        return jdbcTemplate.query(sql, subtaskRowMapper, taskID);
    }
}
