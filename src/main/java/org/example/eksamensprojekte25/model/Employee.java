package org.example.eksamensprojekte25.model;

import java.util.List;

public class Employee {
    private Integer employeeID;
    private String employeeName;
    private String userName;
    private String userPassword;
    private List<Project> managedProjects;

    public Employee(Integer employeeID, String employeeName, String userName, String userPassword) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.userName = userName;
        this.userPassword = userPassword;
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
}
