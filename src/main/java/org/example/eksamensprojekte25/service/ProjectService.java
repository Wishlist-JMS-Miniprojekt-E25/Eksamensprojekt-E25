package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.engine.ElementModelStructureHandler;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //henter alle timeslots
    public List<Timeslot> getAllTimeslots() {
        return projectRepository.getAllTimeslots();
    }

    //Henter alle projekter baseret op employee id, og tildeler hvert projekt assignees
    public List<Project> getProjectsByEmployeeID(Integer employeeID) {

        List<Project> projects = projectRepository.getProjectsByEmployeeID(employeeID);
        for (Project project : projects) {
            project.setAssignedEmployees(projectRepository.getEmployeesByProjectID(project.getProjectID()));
        }
        return projects;
    }

    //henter alle projekter baseret på manager id, og tildeler hvert projekt assignees
    public List<Project> getProjectsByManagerID(Integer managerID) {

        List<Project> projects = projectRepository.getProjectsByManagerID(managerID);
        for (Project project : projects) {
            project.setAssignedEmployees(projectRepository.getEmployeesByProjectID(project.getProjectID()));
        }
        return projects;
    }

    //henter de employees, som er på samme projekt
    public List<Employee> getEmployeesByProjectID(Integer projectID) {
        return projectRepository.getEmployeesByProjectID(projectID);
    }

    public int calculatePlannedDays(Date plannedStartDate, Date plannedFinishDate){
        long differenceInMilliseconds = plannedFinishDate.getTime() - plannedStartDate.getTime();
        return (int) (differenceInMilliseconds / (1000 * 60 * 60 * 24));
        // *Skal* være 1000 milisekunder = 1 sekund
        // 60 sekunder = 1 minut
        // 60 minutter = 1 time
        // 24 timer = 1 dag
        // Det er sådan Date fungerer
    }

    public Project addProject (Integer projectManagerID, String projectName, String projectDescription, Date plannedStartDate, Date plannedFinishDate, List<Integer> employeeIDs){

        int plannedDays = calculatePlannedDays(plannedStartDate, plannedFinishDate);

        Timeslot timeslot = projectRepository.createTimeslot(plannedDays, plannedStartDate, plannedFinishDate);

        Project project = projectRepository.addProject(projectManagerID, projectName, projectDescription, timeslot.getTimeslotID());

        projectRepository.assignEmployeesToProject(project.getProjectID(), employeeIDs);

        List<Employee> employees = projectRepository.getEmployeesByProjectID(project.getProjectID());

        return project;
    }

    //henter et projekt baseret på projekt id
    public Project getProjectByID(Integer projectID) {
        return projectRepository.getProjectByID(projectID);
    }
}
