package org.example.eksamensprojekte25.model;

import java.util.List;

public class Task {
    private String taskName;
    private Integer taskID;
    private String taskDescription;
    private Integer timeslotID;
    private Integer projectID;
    private List<Employee> assignedEmployees;

    public Task(String taskName, List<Employee> assignedEmployees, Integer projectID, Integer timeslotID, String taskDescription, Integer taskID) {
        this.taskName = taskName;
        this.assignedEmployees = assignedEmployees;
        this.projectID = projectID;
        this.timeslotID = timeslotID;
        this.taskDescription = taskDescription;
        this.taskID = taskID;
    }

    public Task() {
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getTimeslotID() {
        return timeslotID;
    }

    public void setTimeslotID(Integer timeslotID) {
        this.timeslotID = timeslotID;
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
