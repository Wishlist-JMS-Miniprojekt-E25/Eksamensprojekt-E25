package org.example.eksamensprojekte25.model;

import java.util.List;

public class Project {

    private Integer projectID;
    private Integer projectManagerID;
    private String projectName;
    private String projectDescription;
    private Timeslot timeslot;
    private List<Employee> assignedEmployees;
    private List<Task> tasks;

    public Project(Integer projectID, Integer projectManagerID, String projectName, String projectDescription, Timeslot timeslot) {
        this.projectID = projectID;
        this.projectManagerID = projectManagerID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.timeslot = timeslot;
    }

    public Project() {

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public Integer getProjectManagerID() {
        return projectManagerID;
    }

    public void setProjectManagerID(Integer projectManagerID) {
        this.projectManagerID = projectManagerID;
    }
}