package org.example.eksamensprojekte25.model;

import java.util.List;

public class Task {
    private Integer taskID;
    private String taskName;
    private String taskDescription;
    private Integer timeslotID;
    private List<Employee> employees;
    private Integer projectID;

    public Task(Integer taskID, String taskName, String taskDescription,  Integer timeslotID,List<Employee> employees,Integer projectID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.timeslotID = timeslotID;
        this.employees = employees;
        this.projectID = projectID;

    }

    public Task() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
