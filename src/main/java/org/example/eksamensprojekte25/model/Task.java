package org.example.eksamensprojekte25.model;

import java.util.List;

public class Task {
    private String taskName;
    private Integer taskID;
    private String taskDescription;
    private Timeslot timeslot;
    private Integer projectID;
    private List<Subtask> subtasks;
    private List<Employee> employees;

    public Task(String taskName, List<Subtask> subtasks, List<Employee> employees, Integer projectID, Timeslot timeslot, String taskDescription, Integer taskID) {
        this.taskName = taskName;
        this.subtasks = subtasks;
        this.employees = employees;
        this.projectID = projectID;
        this.timeslot = timeslot;
        this.taskDescription = taskDescription;
        this.taskID = taskID;
    }

    public Task() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
