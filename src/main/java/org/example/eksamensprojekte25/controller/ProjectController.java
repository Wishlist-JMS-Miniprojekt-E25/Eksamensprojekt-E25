package org.example.eksamensprojekte25.controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.*;
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

    //view for bruger forsiden
    @GetMapping("/userProjects")
    public String showsAllProjects(HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");

        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        List<Project> projectsYouManage = projectService.getProjectsByManagerID(loggedInEmployeeID);
        List<Project> assignedToProjects = projectService.getProjectsByEmployeeID(loggedInEmployeeID);
        List<Timeslot> timeslots = projectService.getAllTimeslots();
        model.addAttribute("employee", loggedInEmployee);
        model.addAttribute("allEmployees",allEmployees);
        model.addAttribute("projectsYouManage", projectsYouManage );
        model.addAttribute("assignedToProjects", assignedToProjects);
        model.addAttribute("timeslots",timeslots);
        return "showsAllProjects";
    }

    //view for ét enkelt projekt
    @GetMapping("/project/{projectID}")
    public String showsProject(@PathVariable int projectID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");

        //Employee manager =
        Project project = projectService.getProjectByID(projectID);
        Employee manager = employeeService.getEmployeeByID(project.getProjectManagerID());
        List<Task> tasks = projectService.getTasksByProjectID(projectID);
        List<Timeslot> timeslots = projectService.getAllTimeslots();
        model.addAttribute("project",project);
        model.addAttribute("tasks", tasks);
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("manager",manager);
        return "showsProject";
    }

    //view for formen for projekt-oprettelse
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

    //får sendt et udfyldt projekt ned til repo'et, der inserter det i databasen
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
        return "redirect:/userProjects";
    }

    //giver et projekt id videre til repo'et, der fjerner selve projektet i databasen
    @PostMapping("/deleteProject/{projectID}")
    public String deleteProject (@PathVariable Integer projectID){

        projectService.deleteProjectByID(projectID);

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

    @GetMapping("/task/{taskID}/addSubtask")
    public String addSubtask(@PathVariable Integer taskID, Model model){

        Subtask subtask = new Subtask();
        subtask.setSubtaskID(taskID);

        model.addAttribute("subtask", subtask);
        model.addAttribute("taskID", taskID);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", allEmployees);

        return "addSubtask";
    }

    @PostMapping("/task/{taskID}/saveSubtask")
    public String saveSubtask (@ModelAttribute Subtask subtask,
                               @RequestParam("plannedStartDate") String plannedStartDate,
                               @RequestParam("plannedFinishDate") String plannedFinishDate){

        Date plannedStartDateForSubtask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForSubtask = Date.valueOf(plannedFinishDate);

        projectService.addSubtask(subtask.getSubtaskName(), subtask.getSubtaskDescription(), subtask.getTaskID(),
                subtask.getEmployeeID(), plannedStartDateForSubtask,
                plannedFinishDateForSubtask);

        return "redirect:/task/" + subtask.getTaskID();
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task,
                           @RequestParam String taskName,
                           @RequestParam String taskDescription,
                           @RequestParam("plannedStartDate") String plannedStartDate,
                           @RequestParam("plannedFinishDate") String plannedFinishDate,
                           @RequestParam(required = false) List<Integer> assignedEmployeeIDs,
                           HttpSession session,
                           Model model) {

        Date plannedStartDateForTask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForTask = Date.valueOf(plannedFinishDate);

        projectService.addTask(taskName, taskDescription,
                plannedStartDateForTask, plannedFinishDateForTask,
                task.getProjectID(),
                assignedEmployeeIDs);

        Integer employeeID = (Integer) session.getAttribute("employeeID");
        return "redirect:/project/" + task.getProjectID();
    }

}
