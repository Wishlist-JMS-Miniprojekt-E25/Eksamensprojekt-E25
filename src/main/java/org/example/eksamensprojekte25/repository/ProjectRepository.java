package org.example.eksamensprojekte25.repository;


import org.example.eksamensprojekte25.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {
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
        return subtask;
    };

    public ProjectRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
