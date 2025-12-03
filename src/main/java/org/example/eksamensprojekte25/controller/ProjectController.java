package org.example.eksamensprojekte25.controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/userProjects")
    public String showsAllProjects(HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");

        Employee employee = employeeService.getEmployeeByID(loggedInEmployeeID);
        List<Project> projectsYouManage = projectService.getProjectsByManagerID(loggedInEmployeeID);
        List<Project> assignedToProjects = projectService.getProjectsByEmployeeID(loggedInEmployeeID);
        List<Timeslot> timeslots = projectService.getAllTimeslots();
        model.addAttribute("employee", employee);
        model.addAttribute("projectsYouManage", projectsYouManage );
        model.addAttribute("assignedToProjects", assignedToProjects);
        model.addAttribute("timeslots",timeslots);
        return "showsAllProjects";
    }
}
