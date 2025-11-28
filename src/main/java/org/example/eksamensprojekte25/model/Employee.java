package org.example.eksamensprojekte25.model;

public class Employee {

    private String employeeName;
    private String userName;
    private String userPassword;
    private Integer employeeID;
    private boolean isManager;

    public Employee(String employeeName, String userName, String userPassword, Integer employeeID, boolean isManager) {
        this.employeeName = employeeName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.employeeID = employeeID;
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

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }


}
