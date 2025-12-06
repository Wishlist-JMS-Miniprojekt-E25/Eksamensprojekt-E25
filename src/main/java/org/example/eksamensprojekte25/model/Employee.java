package org.example.eksamensprojekte25.model;

import java.util.List;

public class Employee {

    private Integer employeeID;
    private String employeeName;
    private String userName;
    private String userPassword;
    private List<Project> managedProjects;

    //isManager skal slettes fra program og database - siger ikke noget om hvad man er manager for...
    private boolean isManager;

    public Employee(Integer employeeID, String employeeName, String userName, String userPassword, boolean isManager) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.isManager = isManager;
    }

    public Employee() {

    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(List<Project> managedProjects) {
        this.managedProjects = managedProjects;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }


}
