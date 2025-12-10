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
                              HttpSession session, Model model) {

        //Konverterer datoer fra string til Date, så vi kan bruge det i if-statement
        Date plannedStartDateForProject = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForProject = Date.valueOf(plannedFinishDate);

        //Tjekker om slutdatoen er før startdatoen
        if(plannedFinishDateForProject.before(plannedStartDateForProject)){
            //Laver en fejlbesked, der sendes til formen
            model.addAttribute("errorMessage", "Planned finish date can not be before planned start date");
            //Sender det indtastede projektdata over
            model.addAttribute("project", project);
            //Sender alle employees, til checklisten
            model.addAttribute("allEmployees", employeeService.getAllEmployees());

            //Gemmer ikke de ændringer der blev lavet (pga fejl) og sender i stedet add-formen igen
            return "addProject";
        }

        //Tjekker om mindst 1 employee er assigned
        if(assignedEmployeeIDs == null || assignedEmployeeIDs.isEmpty()){
            //Sender en fejlbesked, hvis der ikke er blevet assigned mindst 1 (i stedet for at man får whitelabel)
            model.addAttribute("errorMessage", "You must assign at least one employee to the project");
            model.addAttribute("project", project);
            model.addAttribute("allEmployees", employeeService.getAllEmployees());

            return "addProject";
        }

        Integer loggedInEmployeeID = (Integer) session.getAttribute("employeeID");
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
                           @RequestParam Integer projectID, Model model) {
        //Konverterer datoer fra string til Date, så vi kan bruge det i if-statement
        Date plannedStartDateForTask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForTask = Date.valueOf(plannedFinishDate);

        //Henter projektet som tasken skal tilknyttes
        Project project = projectService.getProjectByID(projectID);

        // Tjekker om slutdato er før startdato
        if(plannedFinishDateForTask.before(plannedStartDateForTask)){

            //Sender employees til checklisten
            project.setAssignedEmployees(projectService.getEmployeesByProjectID(projectID));
            task.setProject(project);

            // Sender fejlbesked + data
            model.addAttribute("errorMessage", "Planned finish date can not be before planned start date");
            model.addAttribute("task", task);
            model.addAttribute("projectID", projectID);
            model.addAttribute("plannedStartDate", plannedStartDate);
            model.addAttribute("plannedFinishDate", plannedFinishDate);

            return "addTask";
        }
        //Tjekker om mindst 1 employee er assigned
        if(assignedEmployeeIDs == null || assignedEmployeeIDs.isEmpty()){

            project.setAssignedEmployees(projectService.getEmployeesByProjectID(projectID));
            task.setProject(project);

            //Sender en fejlbesked, hvis der ikke er blevet assigned mindst 1 (i stedet for at man får whitelabel)
            model.addAttribute("errorMessage", "You must assign at least one employee to the task");
            model.addAttribute("task", task);
            model.addAttribute("projectID", projectID);
            model.addAttribute("plannedStartDate", plannedStartDate);
            model.addAttribute("plannedFinishDate", plannedFinishDate);

            return "addTask";
        }

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
                              @RequestParam Integer taskID, Model model) {

        //Konverterer datoerne fra String til Date-objekter
        Date plannedStartDateForSubtask = Date.valueOf(plannedStartDate);
        Date plannedFinishDateForSubtask = Date.valueOf(plannedFinishDate);

        //Henter tasken subtasken skal tilknyttes
        Task task = projectService.getTaskByID(taskID);

        // Tjekker om slutdato ligger før startdato
        if(plannedFinishDateForSubtask.before(plannedStartDateForSubtask)){

            //Henter employees til checklisten
            task.setAssignedEmployees(projectService.getEmployeesByTaskID(taskID));
            subtask.setTask(task);

            // Sender fejlbesked og inputdata tilbage til formen
            model.addAttribute("errorMessage", "Planned finish date can not be before planned start date");
            model.addAttribute("subtask", subtask);
            model.addAttribute("taskID", taskID);
            model.addAttribute("plannedStartDate", plannedStartDate);
            model.addAttribute("plannedFinishDate", plannedFinishDate);

            return "addSubtask";
        }

        //Tjekker om der er valgt en employee (en subtask kan kun have én)
        if(assignedEmployeeID == null){

            //Henter employees til radio-listen/(checklisten)
            task.setAssignedEmployees(projectService.getEmployeesByTaskID(taskID));
            subtask.setTask(task);

            // Fejlbesked hvis ingen employee er valgt
            model.addAttribute("errorMessage", "You must assign one employee to the subtask");
            model.addAttribute("subtask", subtask);
            model.addAttribute("taskID", taskID);
            model.addAttribute("plannedStartDate", plannedStartDate);
            model.addAttribute("plannedFinishDate", plannedFinishDate);

            return "addSubtask";
        }

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
    public String updateProject(@ModelAttribute Project project,
                                @RequestParam List<Integer> assignedEmployeeIDs, Model model) {

        //Konverterer datoer fra string til Date, så vi kan bruge det i if-statement
        Date plannedStart = project.getTimeslot().getPlannedStartDate();
        Date plannedFinish = project.getTimeslot().getPlannedFinishDate();

        //Tjekker om slutdatoen er før startdatoen
        if(plannedFinish.before(plannedStart)){
            //Laver en fejlbesked, der sendes til formen
            model.addAttribute("errorMessage", "Planned finish date can not be before planned start date");
            //Sender det indtastede projektdata over
            model.addAttribute("project", project);
            //Sender alle employees, til checklisten
            model.addAttribute("allEmployees", employeeService.getAllEmployees());
            //Sørger for at de valgte employees stadig er checked
            model.addAttribute("assignedEmployeeIDs", assignedEmployeeIDs);

            return "editProject";

        }

        projectService.editProject(project, assignedEmployeeIDs);

        return "redirect:/userOptions";
    }

    @GetMapping("/editSubtask/{subtaskID}")
    public String editSubtask(@PathVariable Integer subtaskID, Model model) {
        Subtask subtask = projectService.getSubtaskByID(subtaskID);
        model.addAttribute("subtask", subtask);
        return "editSubtask";
    }

    @PostMapping("/updateSubtask")
    public String updateSubtask(@ModelAttribute Subtask subtask, Model model) {

        Date plannedStart = subtask.getTimeslot().getPlannedStartDate();
        Date plannedFinish = subtask.getTimeslot().getPlannedFinishDate();

        if(plannedFinish.before(plannedStart)){

            model.addAttribute("errorMessage", "Planned finish date can not be before planned start date");
            model.addAttribute("subtask", subtask);

            subtask.getTask().setAssignedEmployees(projectService.getEmployeesByTaskID(subtask.getTask().getTaskID()));

            return "editSubtask";
        }

        projectService.editSubtask(subtask);
        return "redirect:/task/" + subtask.getTask().getTaskID();
    }

    @GetMapping("/editTask/{taskID}")
    public String editTask(@PathVariable Integer taskID, Model model) {

        //Henter det projekt, der skal redigeres
        Task task = projectService.getTaskByID(taskID);

        model.addAttribute("task", task);

        Integer projectID = task.getProject().getProjectID();
        List<Employee> employeesAssignedToProject = projectService.getEmployeesByProjectID(projectID);

        model.addAttribute("employeesAssignedToProject", employeesAssignedToProject);

        //Opretter listen med de allerede assigned employees, så de kan være checked
        List<Integer> assignedIDs = new ArrayList<>();
        for (Employee e : task.getAssignedEmployees()) {
            assignedIDs.add(e.getEmployeeID());
        }

        model.addAttribute("assignedEmployeeIDs", assignedIDs);

        return "editTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task, @RequestParam List<Integer> assignedEmployeeIDs, Model model) {

        Date plannedStart = task.getTimeslot().getPlannedStartDate();
        Date plannedFinish = task.getTimeslot().getPlannedFinishDate();

        if(plannedFinish.before(plannedStart)){

            model.addAttribute("errorMessage", "Planned finish date can not be before planned start date");
            model.addAttribute("task", task);
            model.addAttribute("employeesAssignedToProject", projectService.getEmployeesByProjectID(task.getProject().getProjectID()));
            model.addAttribute("assignedEmployeeIDs", assignedEmployeeIDs);

            return "editTask";
        }

        projectService.editTask(task, assignedEmployeeIDs);

        return "redirect:/project/" + task.getProject().getProjectID();
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
        for (Subtask subtask : task.getSubtasks()) {
            projectService.deleteSubtaskByID(subtask.getSubtaskID());
        }
        return "redirect:/project/" + task.getProject().getProjectID();
    }

    @PostMapping("/finalizeProject/{projectID}")
    public String finalizeProject(@PathVariable Integer projectID) {
        Project project = projectService.getProjectByID(projectID);
        projectService.finalizeProject(project);
        for (Task task : project.getTasks()) {
            projectService.deleteTaskByID(task.getTaskID());
            for (Subtask subtask : task.getSubtasks()) {
                projectService.deleteSubtaskByID(subtask.getSubtaskID());
            }
        }
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
        model.addAttribute("archivedTask", archivedTask);
        return "archivedTask";
    }
}