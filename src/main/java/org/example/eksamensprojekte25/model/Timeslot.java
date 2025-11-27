package org.example.eksamensprojekte25.model;

import java.util.Date;

public class Timeslot {
    private Integer timeslotID;
    private Integer plannedDays;
    private Date plannedStartDate;
    private Date plannedFinishDate;
    private Date actualFinishDate;
    private Integer differenceInDays;
    private Integer totalWorkhours;
    private boolean isDone;


    public Timeslot(int plannedDays, Date plannedStartDate, Date plannedFinishDate, Integer timeslotID, Date actualFinishDate, int differenceInDays, int totalWorkhours, boolean isDone) {
        this.plannedDays = plannedDays;
        this.plannedStartDate = plannedStartDate;
        this.plannedFinishDate = plannedFinishDate;
        this.timeslotID = timeslotID;
        this.actualFinishDate = actualFinishDate;
        this.differenceInDays = differenceInDays;
        this.totalWorkhours = totalWorkhours;
        this.isDone = isDone;
    }


    public Timeslot() {
        plannedDays = null;
        isDone = false;
        differenceInDays = null;
        totalWorkhours = null;
        actualFinishDate = null;
    }

    public Integer getTimeslotID() {
        return timeslotID;
    }

    public void setTimeslotID(Integer timeslotID) {
        this.timeslotID = timeslotID;
    }

    public Integer getPlannedDays() {
        return plannedDays;
    }

    public void setPlannedDays(Integer plannedDays) {
        this.plannedDays = plannedDays;
    }

    public Date getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Date plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Date getPlannedFinishDate() {
        return plannedFinishDate;
    }

    public void setPlannedFinishDate(Date plannedFinishDate) {
        this.plannedFinishDate = plannedFinishDate;
    }

    public Date getActualFinishDate() {
        return actualFinishDate;
    }

    public void setActualFinishDate(Date actualFinishDate) {
        this.actualFinishDate = actualFinishDate;
    }

    public Integer getDifferenceInDays() {
        return differenceInDays;
    }

    public void setDifferenceInDays(Integer differenceInDays) {
        this.differenceInDays = differenceInDays;
    }

    public Integer getTotalWorkhours() {
        return totalWorkhours;
    }

    public void setTotalWorkhours(Integer totalWorkhours) {
        this.totalWorkhours = totalWorkhours;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
