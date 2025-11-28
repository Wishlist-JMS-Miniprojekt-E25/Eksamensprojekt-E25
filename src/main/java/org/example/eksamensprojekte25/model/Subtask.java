package org.example.eksamensprojekte25.model;

public class Subtask {
    private String subtaskName;
    private Integer subtaskID;
    private String subtaskDescription;
    private Integer timeslotID;
    private Integer taskID;
    private Integer employeeID;

    public Subtask(String subtaskName, Integer subtaskID, String subtaskDescription, Integer timeslotID, Integer taskID, Integer employeeID) {
        this.subtaskName = subtaskName;
        this.subtaskID = subtaskID;
        this.subtaskDescription = subtaskDescription;
        this.timeslotID = timeslotID;
        this.taskID = taskID;
        this.employeeID = employeeID;
    }

    public Subtask() {
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Integer getTimeslotID() {
        return timeslotID;
    }

    public void setTimeslotID(Integer timeslotID) {
        this.timeslotID = timeslotID;
    }

    public String getSubtaskDescription() {
        return subtaskDescription;
    }

    public void setSubtaskDescription(String subtaskDescription) {
        this.subtaskDescription = subtaskDescription;
    }

    public Integer getSubtaskID() {
        return subtaskID;
    }

    public void setSubtaskID(Integer subtaskID) {
        this.subtaskID = subtaskID;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }
}
