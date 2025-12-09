package org.example.eksamensprojekte25.model;

public class Subtask {
    private Integer subtaskID;
    private String subtaskName;
    private String subtaskDescription;
    private Timeslot timeslot;
    private Task task;
    private Employee assignedEmployee;

    public Subtask(Integer subtaskID, String subtaskName, String subtaskDescription, Timeslot timeslot, Task task, Employee assignedEmployee) {
        this.subtaskID = subtaskID;
        this.subtaskName = subtaskName;
        this.subtaskDescription = subtaskDescription;
        this.timeslot = timeslot;
        this.task = task;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
