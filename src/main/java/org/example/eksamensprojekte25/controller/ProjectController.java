package org.example.eksamensprojekte25.controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping()
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects/{employeeID}")
    public String showAllProjectsByEmployeeID(@PathVariable Integer employeeID, Model model) {

        List<Project> projects = projectService.getProjectsByEmployeeID(employeeID);
        model.addAttribute("projects", projects);

        List<Timeslot> timeslots = projectService.getAllTimeslots();
        model.addAttribute("timeslots",timeslots);
        return "showAllProjects";
    }
}
