package org.example.eksamensprojekte25.controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @GetMapping("/projects")
    public String showAllProjectsByEmployeeID(HttpSession session, Model model) {
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

        if (currentEmployeeID == null) {
            return "redirect:/";
        }

        List<Project> projects = projectService.getProjectsByEmployeeID(currentEmployeeID);
        model.addAttribute("projects", projects);

        List<Timeslot> timeslots = projectService.getAllTimeslots();
        model.addAttribute("timeslots",timeslots);

        model.addAttribute("loggedInEmployee", currentEmployeeID);
        return "showAllProjectsByEmployeeID";
    }

    @GetMapping("/addProject")
    public String addProject (HttpSession session, Model model){
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

        if (currentEmployeeID == null) {
            return "redirect:/";
        }
        Project project = new Project();
        project.setProjectManagerID(currentEmployeeID);
        model.addAttribute("project", project);
        model.addAttribute("projectManager", currentEmployeeID);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", allEmployees);

        return "addProject";
    }

    @PostMapping("/saveProject")
    public String saveProject (@ModelAttribute Project project,
                               @RequestParam(value = "assignedEmployeeIDs", required = false) List<Integer> assignedEmployeeIDs,
                               @RequestParam("plannedStartDate") String plannedStartDate,
                               @RequestParam("plannedFinishDate") String plannedFinishDate,
                               HttpSession session){
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

        if (currentEmployeeID == null) {
            return "redirect:/";
        }

        Date plannedStartDateForProject = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForProject = Date.valueOf(plannedFinishDate);

        projectService.addProject(currentEmployeeID, project.getProjectName(), project.getProjectDescription(), plannedStartDateForProject, plannedFinishDateForProject, assignedEmployeeIDs);
        return "redirect:/projects/" + currentEmployeeID;
    }

    @PostMapping("/deleteProject/{projectID}")
    public String deleteProject (@PathVariable Integer projectID, HttpSession session){
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

        if (currentEmployeeID == null) {
            return "redirect:/";
        }

        Project project = projectService.getProjectByID(projectID);
        projectService.deleteProjectByID(projectID);

        return "redirect:/projects";
    }
}
