package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(Integer employeeID) {
        return projectRepository.getProjects(employeeID);
    }
}
