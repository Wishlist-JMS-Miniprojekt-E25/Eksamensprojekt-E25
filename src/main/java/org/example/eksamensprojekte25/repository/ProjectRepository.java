package org.example.eksamensprojekte25.repository;


import org.example.eksamensprojekte25.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {


    public ProjectRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setProjectID(rs.getInt("projectID"));
        project.setProjectManagerID(rs.getInt("projectManagerID"));
        project.setProjectName(rs.getString("projectName"));
        project.setProjectDescription(rs.getString("projectDescription"));

        Timeslot timeslot = new Timeslot();
        timeslot.setTimeslotID(rs.getInt("timeslotID"));
        timeslot.setPlannedDays(rs.getInt("plannedDays"));
        timeslot.setPlannedStartDate(rs.getDate("plannedStartDate"));
        timeslot.setPlannedFinishDate(rs.getDate("plannedFinishDate"));
        timeslot.setActualFinishDate(rs.getDate("actualFinishDate"));
        timeslot.setDifferenceInDays(rs.getInt("differenceInDays"));
        timeslot.setTotalWorkhours(rs.getInt("totalWorkhours"));
        timeslot.setDone(rs.getBoolean("isDone"));

        project.setTimeslot(timeslot);

        return project;
    };

    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setTaskID(rs.getInt("taskID"));
        task.setTaskName(rs.getString("taskName"));
        task.setTaskDescription(rs.getString("taskDescription"));
        task.setProjectID(rs.getInt("projectID"));


        Timeslot timeslot = new Timeslot();
        timeslot.setTimeslotID(rs.getInt("timeslotID"));
        timeslot.setPlannedDays(rs.getInt("plannedDays"));
        timeslot.setPlannedStartDate(rs.getDate("plannedStartDate"));
        timeslot.setPlannedFinishDate(rs.getDate("plannedFinishDate"));
        timeslot.setActualFinishDate(rs.getDate("actualFinishDate"));
        timeslot.setDifferenceInDays(rs.getInt("differenceInDays"));
        timeslot.setTotalWorkhours(rs.getInt("totalWorkhours"));
        timeslot.setDone(rs.getBoolean("isDone"));

        task.setTimeslot(timeslot);

        return task;
    };

    private final RowMapper<Subtask> subtaskRowMapper = (rs, rowNum) -> {
        Subtask subtask = new Subtask();
        subtask.setSubtaskID(rs.getInt("subtaskID"));
        subtask.setSubtaskName(rs.getString("subtaskName"));
        subtask.setSubtaskDescription(rs.getString("subtaskDescription"));
        subtask.setTaskID(rs.getInt("taskID"));
        subtask.setEmployeeID(rs.getInt("employeeID"));


        Timeslot timeslot = new Timeslot();
        timeslot.setTimeslotID(rs.getInt("timeslotID"));
        timeslot.setPlannedDays(rs.getInt("plannedDays"));
        timeslot.setPlannedStartDate(rs.getDate("plannedStartDate"));
        timeslot.setPlannedFinishDate(rs.getDate("plannedFinishDate"));
        timeslot.setActualFinishDate(rs.getDate("actualFinishDate"));
        timeslot.setDifferenceInDays(rs.getInt("differenceInDays"));
        timeslot.setTotalWorkhours(rs.getInt("totalWorkhours"));
        timeslot.setDone(rs.getBoolean("isDone"));

        subtask.setTimeslot(timeslot);

        return subtask;
    };


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
            WHERE
        """;

        return jdbcTemplate.query(sql, projectRowMapper,employeeID);
    }


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
