package org.example.eksamensprojekte25.controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Task;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    //viser bruger forsiden
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

    @GetMapping("/addProject")
    public String addProject (HttpSession session, Model model){
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

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

        Date plannedStartDateForProject = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForProject = Date.valueOf(plannedFinishDate);

        projectService.addProject(currentEmployeeID, project.getProjectName(), project.getProjectDescription(), plannedStartDateForProject, plannedFinishDateForProject, assignedEmployeeIDs);
        return "redirect:/userProjects"; //skal reelt set redirecte til view 3 i vore UX
    }

    @GetMapping("/addTask/{projectID}")
    public String showAddTaskForm(@PathVariable Integer projectID, Model model) {

        Task task = new Task();
        task.setProjectID(projectID);

        model.addAttribute("task", task);


        List<Employee> projectEmployees = projectService.getEmployeesByProjectID(projectID);
        model.addAttribute("projectEmployees", projectEmployees);


        model.addAttribute("timeslots", projectService.getAllTimeslots());

        return "addTask";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task,
                           @RequestParam String taskName,
                           @RequestParam String taskDescription,
                           @RequestParam Date plannedStartDate,
                           @RequestParam Date plannedFinishDate,
                           @RequestParam(required = false) List<Integer> assignedEmployeeIDs,
                           HttpSession session,
                           Model model) {

        projectService.addTask(taskName, taskDescription,
                plannedStartDate, plannedFinishDate,
                task.getProjectID(),
                assignedEmployeeIDs);

        Integer employeeID = (Integer) session.getAttribute("employeeID");
        return "redirect:/userProjects";
    }

}
