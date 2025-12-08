package org.example.eksamensprojekte25.model;

public class Subtask {
    private Integer subtaskID;
    private String subtaskName;
    private String subtaskDescription;
    private Timeslot timeslot;
    private Integer taskID;
    private Employee assignedEmployee;

    public Subtask(Integer subtaskID, String subtaskName, String subtaskDescription, Timeslot timeslot, Integer taskID, Employee assignedEmployee) {
        this.subtaskID = subtaskID;
        this.subtaskName = subtaskName;
        this.subtaskDescription = subtaskDescription;
        this.timeslot = timeslot;
        this.taskID = taskID;
        this.assignedEmployee = assignedEmployee;
    }

    public Subtask() {
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
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
