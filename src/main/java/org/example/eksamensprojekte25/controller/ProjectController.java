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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Project> assignedToProjects = employeeService.getProjectsByEmployeeID(loggedInEmployeeID);
        model.addAttribute("loggedInEmployee", loggedInEmployee);
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("assignedToProjects", assignedToProjects);
        return "showsAllProjects";
    }

    //view for ét enkelt projekt
    @GetMapping("/project/{projectID}")
    public String showsProject(@PathVariable int projectID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Project project = projectService.getProjectByID(projectID);
        Employee manager = employeeService.getEmployeeByID(project.getProjectManagerID());
        model.addAttribute("project", project);
        model.addAttribute("manager", manager);
        model.addAttribute("loggedInEmployee",loggedInEmployeeID);
        return "showsProject";
    }

    //view for én enkelt task
    @GetMapping("/task/{taskID}")
    public String showsTask(@PathVariable int taskID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Project project = projectService.getProjectByTaskID(taskID);
        Employee manager = employeeService.getEmployeeByID(project.getProjectManagerID());
        Task task = projectService.getTaskByID(taskID);
        model.addAttribute("project", project);
        model.addAttribute("manager", manager);
        model.addAttribute("task", task);
        model.addAttribute("loggedInEmployee",loggedInEmployeeID);
        return "showsTask";
    }

    //view for formen for projekt-oprettelse
    @GetMapping("/addProject")
    public String addProject(HttpSession session, Model model) {
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
    public String saveProject(@ModelAttribute Project project,
                              @RequestParam(value = "assignedEmployeeIDs", required = false) List<Integer> assignedEmployeeIDs,
                              @RequestParam("plannedStartDate") String plannedStartDate,
                              @RequestParam("plannedFinishDate") String plannedFinishDate,
                              HttpSession session) {
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

        Date plannedStartDateForProject = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForProject = Date.valueOf(plannedFinishDate);

        projectService.addProject(currentEmployeeID, project.getProjectName(), project.getProjectDescription(), plannedStartDateForProject, plannedFinishDateForProject, assignedEmployeeIDs);
        return "redirect:/userProjects";
    }

    //giver et projekt id videre til repo'et, der fjerner selve projektet i databasen
    @PostMapping("/deleteProject/{projectID}")
    public String deleteProject(@PathVariable Integer projectID) {

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

        return "addTask";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task,
                           @RequestParam String taskName,
                           @RequestParam String taskDescription,
                           @RequestParam("plannedStartDate") String plannedStartDate,
                           @RequestParam("plannedFinishDate") String plannedFinishDate,
                           @RequestParam(required = false) List<Integer> assignedEmployeeIDs) {

        Date plannedStartDateForTask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForTask = Date.valueOf(plannedFinishDate);

        projectService.addTask(taskName, taskDescription,
                plannedStartDateForTask, plannedFinishDateForTask,
                task.getProjectID(),
                assignedEmployeeIDs);

        return "redirect:/project/" + task.getProjectID();
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable Integer taskID, RedirectAttributes redirectAttributes) {
        Task task = projectService.getTaskByID(taskID);
        projectService.deleteTaskByID(taskID);

        redirectAttributes.addAttribute("projectID", task.getProjectID());
        return "redirect:/project/{projectID}";
    }

    @GetMapping("/task/{taskID}/addSubtask")
    public String addSubtask(@PathVariable Integer taskID, Model model) {

        Subtask subtask = new Subtask();
        subtask.setTaskID(taskID);

        model.addAttribute("subtask", subtask);
        model.addAttribute("taskID", taskID);

        List<Employee> taskEmployees = projectService.getEmployeesByTaskID(taskID);
        model.addAttribute("taskEmployees", taskEmployees);

        return "addSubtask";
    }

    @PostMapping("/task/{taskID}/saveSubtask")
    public String saveSubtask(@ModelAttribute Subtask subtask,
                              @RequestParam("plannedStartDate") String plannedStartDate,
                              @RequestParam("plannedFinishDate") String plannedFinishDate) {

        Date plannedStartDateForSubtask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForSubtask = Date.valueOf(plannedFinishDate);

        projectService.addSubtask(subtask.getSubtaskName(), subtask.getSubtaskDescription(), subtask.getTaskID(),
                subtask.getAssignedEmployee().getEmployeeID(), plannedStartDateForSubtask,
                plannedFinishDateForSubtask);

        return "redirect:/task/" + subtask.getTaskID();
    }



    @PostMapping("task/{taskID}/deleteSubtask/{subtaskID}")
    public String deleteSubtask(@PathVariable Integer taskID, @PathVariable Integer subtaskID) {
        projectService.deleteSubtaskByID(subtaskID);

        return "redirect:/task/" + taskID;
    }

    @GetMapping("/editProject/{projectID}")
    public String editProject (@PathVariable Integer projectID, Model model){

        Project project = projectService.getProjectByID(projectID);

        model.addAttribute("project", project);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", allEmployees);

        return "editProject";
    }

    @PostMapping("/updateProject")
    public String updateProject (@ModelAttribute Project project, @RequestParam List<Integer> assignedEmployeeIDs){


        projectService.editProject(project, assignedEmployeeIDs);

        return "redirect:/project/" + project.getProjectID();
    }
}