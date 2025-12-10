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
import java.util.List;
import java.util.ArrayList;

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
    @GetMapping("/userOptions")
    public String showsAllProjects(HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);

        // ➜ Hvis HR er logget ind → vis HR-siden i stedet
        if (loggedInEmployee.getUserName().equalsIgnoreCase("HR")) {

            model.addAttribute("employees", employeeService.getAllEmployees());
            return "HRPage"; // <-- ny HTML-side kun for HR
        }

        List<Project> assignedToProjects = employeeService.getProjectsByEmployeeID(loggedInEmployeeID);
        model.addAttribute("loggedInEmployee", loggedInEmployee);
        model.addAttribute("assignedToProjects", assignedToProjects);
        return "showsAllProjects";
    }

    //view for ét enkelt projekt
    @GetMapping("/project/{projectID}")
    public String showsProject(@PathVariable int projectID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);
        Project project = projectService.getProjectByID(projectID);
        model.addAttribute("project", project);
        model.addAttribute("loggedInEmployee", loggedInEmployee);
        return "showsProject";
    }

    //view for én enkelt task
    @GetMapping("/task/{taskID}")
    public String showsTask(@PathVariable int taskID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);
        Task task = projectService.getTaskByID(taskID);
        model.addAttribute("task", task);
        model.addAttribute("loggedInEmployee", loggedInEmployee);
        return "showsTask";
    }

    //view for formen for projekt-oprettelse
    @GetMapping("/addProject")
    public String addProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);

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
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");

        Date plannedStartDateForProject = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForProject = Date.valueOf(plannedFinishDate);

        projectService.addProject(loggedInEmployeeID, project.getProjectName(), project.getProjectDescription(), plannedStartDateForProject, plannedFinishDateForProject, assignedEmployeeIDs);
        return "redirect:/userOptions";
    }

    //giver et projekt id videre til repo'et, der fjerner selve projektet i databasen
    @PostMapping("/deleteProject/{projectID}")
    public String deleteProject(@PathVariable Integer projectID) {

        projectService.deleteProjectByID(projectID);

        return "redirect:/userOptions"; //skal reelt set redirecte til view 3 i vore UX
    }

    @GetMapping("/addTask/{projectID}")
    public String showAddTaskForm(@PathVariable Integer projectID, Model model) {

        Task task = new Task();
        task.setProject(projectService.getProjectByID(projectID));
        model.addAttribute("task", task);

        return "addTask";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task,
                           @RequestParam String taskName,
                           @RequestParam String taskDescription,
                           @RequestParam("plannedStartDate") String plannedStartDate,
                           @RequestParam("plannedFinishDate") String plannedFinishDate,
                           @RequestParam(required = false) List<Integer> assignedEmployeeIDs,
                           @RequestParam Integer projectID) {

        Date plannedStartDateForTask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForTask = Date.valueOf(plannedFinishDate);

        Project project = projectService.getProjectByID(projectID);

        projectService.addTask(taskName, taskDescription,
                plannedStartDateForTask, plannedFinishDateForTask,
                project.getProjectID(),
                assignedEmployeeIDs);

        return "redirect:/project/" + project.getProjectID();
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable Integer taskID, RedirectAttributes redirectAttributes) {
        Task task = projectService.getTaskByID(taskID);
        projectService.deleteTaskByID(taskID);

        redirectAttributes.addAttribute("projectID", task.getProject().getProjectID());
        return "redirect:/project/{projectID}";
    }

    @GetMapping("/task/{taskID}/addSubtask")
    public String addSubtask(@PathVariable Integer taskID, Model model) {

        Subtask subtask = new Subtask();
        subtask.setTask(projectService.getTaskByID(taskID));
        model.addAttribute("subtask", subtask);

        return "addSubtask";
    }

    @PostMapping("/task/saveSubtask")
    public String saveSubtask(@ModelAttribute Subtask subtask,
                              @RequestParam("plannedStartDate") String plannedStartDate,
                              @RequestParam("plannedFinishDate") String plannedFinishDate,
                              @RequestParam(required = false) Integer assignedEmployeeID,
                              @RequestParam Integer taskID) {

        Date plannedStartDateForSubtask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForSubtask = Date.valueOf(plannedFinishDate);

        Task task = projectService.getTaskByID(taskID);

        projectService.addSubtask(subtask.getSubtaskName(), subtask.getSubtaskDescription(), task.getTaskID(),
                assignedEmployeeID, plannedStartDateForSubtask,
                plannedFinishDateForSubtask);

        return "redirect:/task/" + task.getTaskID();
    }

    @PostMapping("/deleteSubtask/{subtaskID}")
    public String deleteSubtask(@PathVariable Integer subtaskID) {
        Subtask subtask = projectService.getSubtaskByID(subtaskID);
        projectService.deleteSubtaskByID(subtaskID);

        return "redirect:/task/" + subtask.getTask().getTaskID();
    }

    @GetMapping("/editProject/{projectID}")
    public String editProject(@PathVariable Integer projectID, Model model) {

        //Henter det projekt, der skal redigeres
        Project project = projectService.getProjectByID(projectID);

        model.addAttribute("project", project);

        //Henter alle employees til checklisten
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", allEmployees);

        //Opretter listen med de allerede assigned employees, så de kan være checked
        List<Integer> assignedIDs = new ArrayList<>();
        for (Employee e : project.getAssignedEmployees()) {
            assignedIDs.add(e.getEmployeeID());
        }

        model.addAttribute("assignedEmployeeIDs", assignedIDs);

        return "editProject";
    }

    @PostMapping("/updateProject")
    public String updateProject(@ModelAttribute Project project, @RequestParam List<Integer> assignedEmployeeIDs) {


        projectService.editProject(project, assignedEmployeeIDs);

        return "redirect:/userOptions";
    }

    @GetMapping("/subtaskWorkhours/{subtaskID}")
    public String subtaskWorkhours(@PathVariable Integer subtaskID, Model model) {

        Subtask subtask = projectService.getSubtaskByID(subtaskID);


        model.addAttribute("subtask", subtask);

        return "subtaskWorkhoursForm";
    }

    @PostMapping("/finalizeSubtask/{subtaskID}")
    public String finalizeSubtask(@PathVariable Integer subtaskID, @RequestParam Integer totalWorkhours) {

        Subtask subtask = projectService.getSubtaskByID(subtaskID);

        subtask.getTimeslot().setTotalWorkhours(totalWorkhours);

        projectService.finalizeSubtask(subtask);

        return "redirect:/task/" + subtask.getTask().getTaskID();
    }

    @GetMapping("/taskWorkhours/{taskID}")
    public String taskWorkhours(@PathVariable Integer taskID, Model model) {
        Task task = projectService.getTaskByID(taskID);
        if (task.getTimeslot().getTotalWorkhours() > 0) {
            projectService.finalizeTaskWithoutTotalWorkhours(task);
            return "redirect:/project/" + task.getProject().getProjectID();
        }
        model.addAttribute("task", task);
        return "taskWorkhoursForm";
    }

    @PostMapping("/finalizeTask/{taskID}")
    public String finalizeTask(@PathVariable Integer taskID, @RequestParam Integer totalWorkhours) {
        Task task = projectService.getTaskByID(taskID);
        task.getTimeslot().setTotalWorkhours(totalWorkhours);
        projectService.finalizeTask(task);
        return "redirect:/project/" + task.getProject().getProjectID();
    }

    @PostMapping("/finalizeProject/{projectID}")
    public String finalizeProject(@PathVariable Integer projectID) {
        Project project = projectService.getProjectByID(projectID);
        projectService.finalizeProject(project);
        return "redirect:/userOptions";
    }

    @GetMapping("/archivedProjects")
    public String showAllArchivedProjects(HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);

        List<Project> archivedProjects = projectService.getArchivedProjects(loggedInEmployeeID);
        model.addAttribute("loggedInEmployee", loggedInEmployee);
        model.addAttribute("archivedProjects", archivedProjects);
        return "allArchivedProjects";
    }

    @GetMapping("/archivedProject/{projectID}")
    public String showArchivedProject(@PathVariable Integer projectID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);

        Project archivedProject = projectService.getArchivedProjectByID(projectID);
        model.addAttribute("loggedInEmployee", loggedInEmployee);
        model.addAttribute("archivedProject", archivedProject);
        return "archivedProject";
    }

    @GetMapping("/archivedTask/{taskID}")
    public String showArchivedTask(@PathVariable Integer taskID, HttpSession session, Model model) {
        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
        Employee loggedInEmployee = employeeService.getEmployeeByID(loggedInEmployeeID);
        model.addAttribute("loggedInEmployee", loggedInEmployee);

        Task archivedTask = projectService.getArchivedTaskByID(taskID);
        model.addAttribute("archivedTask",archivedTask);
        return "archivedTask";
    }
}