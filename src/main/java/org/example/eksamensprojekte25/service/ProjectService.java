package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.springframework.stereotype.Service;

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

    //henter et projekt baseret på projekt id
    public Project getProjectByID(Integer projectID) {
        return projectRepository.getProjectByID(projectID);
    }
}
