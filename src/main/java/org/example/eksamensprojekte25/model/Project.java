package org.example.eksamensprojekte25.model;

import java.util.List;

public class Project {

    private String projectName;
    private Integer projectID;
    private String projectDescription;
    private Integer timeslotID;
    private List<Employee> assignedEmployees;
    private Integer projectManagerID;


    public Project(String projectName, Integer projectID, String projectDescription, Integer timeslotID, List<Employee> assignedEmployees, Integer projectManagerID) {
        this.projectName = projectName;
        this.projectID = projectID;
        this.projectDescription = projectDescription;
        this.timeslotID = timeslotID;
        this.assignedEmployees = assignedEmployees;
        this.projectManagerID = projectManagerID;
    }

    public Project() {

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

    public Integer getTimeslotID() {
        return timeslotID;
    }

    public void setTimeslotID(Integer timeslotID) {
        this.timeslotID = timeslotID;
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