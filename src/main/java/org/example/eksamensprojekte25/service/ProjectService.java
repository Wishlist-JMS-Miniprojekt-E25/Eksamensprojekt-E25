package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.*;
import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public int calculatePlannedDays(Date plannedStartDate, Date plannedFinishDate) {
        long differenceInMilliseconds = plannedFinishDate.getTime() - plannedStartDate.getTime();
        return (int) (differenceInMilliseconds / (1000 * 60 * 60 * 24));
        // *Skal* være 1000 milisekunder = 1 sekund
        // 60 sekunder = 1 minut
        // 60 minutter = 1 time
        // 24 timer = 1 dag
        // Det er sådan Date fungerer
        //metoden returnerer forskellen i dage
    }

    //henter de employees, som er på samme projekt
    public List<Employee> getEmployeesByProjectID(Integer projectID) {
        return projectRepository.getEmployeesByProjectID(projectID);
    }

    //henter de employees, som er på samme task
    public List<Employee> getEmployeesByTaskID(Integer taskID) {
        return projectRepository.getEmployeesByTaskID(taskID);
    }

    //henter et projekt baseret på projekt id, fylder assignedemployees, task og subtask lister op
    public Project getProjectByID(Integer projectID) {
        Project project = projectRepository.getProjectByID(projectID);
        populateListOfAssignedEmployeesOfProject(project);

        populateListOfTasksForProject(project);
        for (Task task : project.getTasks()) {
            populateListOfSubtasksForTask(task);
            populateListOfAssignedEmployeesOfTask(task);
        }
        return project;
    }

    public Project addProject(Integer projectManagerID,
                              String projectName,
                              String projectDescription,
                              Date plannedStartDate,
                              Date plannedFinishDate,
                              List<Integer> employeeIDs) {
        int plannedDays = calculatePlannedDays(plannedStartDate, plannedFinishDate);
        Timeslot timeslot = projectRepository.createTimeslot(plannedDays, plannedStartDate, plannedFinishDate);
        Project project = projectRepository.addProject(projectManagerID, projectName, projectDescription, timeslot.getTimeslotID());
        project.setTimeslot(timeslot);
        projectRepository.assignEmployeesToProject(project.getProjectID(), employeeIDs);
        return project;
    }

    public void deleteProjectByID(Integer projectID) {
        projectRepository.deleteProjectByID(projectID);
    }

    //henter et projekt baseret på task id, fylder assignedemployees liste og task lise op
    public Task getTaskByID(Integer taskID) {
        Task task = projectRepository.getTaskByID(taskID);
        List<Employee> employees = projectRepository.getEmployeesByTaskID(taskID);
        task.setAssignedEmployees(employees);
        populateListOfSubtasksForTask(task);
        Project project = task.getProject();
        populateListOfAssignedEmployeesOfProject(project);
        return task;
    }

    public Task addTask(String taskName,
                        String taskDescription,
                        Date plannedStartDate,
                        Date plannedFinishDate,
                        Integer projectID,
                        List<Integer> employeeIDs) {
        int plannedDays = calculatePlannedDays(plannedStartDate, plannedFinishDate);
        Timeslot timeslot = projectRepository.createTimeslot(
                plannedDays, plannedStartDate, plannedFinishDate);

        Task task = projectRepository.addTask(
                taskName, taskDescription, timeslot.getTimeslotID(), projectID);

        projectRepository.assignEmployeesToTask(task.getTaskID(), employeeIDs);
        return task;
    }

    public void deleteTaskByID(Integer taskID) {
        projectRepository.deleteTaskByID(taskID);
    }

    public Subtask getSubtaskByID(Integer subtaskID) {
        return projectRepository.getSubtaskByID(subtaskID);
    }

    public Subtask addSubtask(String subtaskName,
                              String subtaskDescription,
                              Integer taskID, Integer employeeID,
                              Date plannedStartDate,
                              Date plannedFinishDate) {
        int plannedDays = calculatePlannedDays(plannedStartDate, plannedFinishDate);
        Timeslot timeslot = projectRepository.createTimeslot(plannedDays, plannedStartDate, plannedFinishDate);
        Subtask subtask = projectRepository.addSubtask(subtaskName, subtaskDescription, timeslot.getTimeslotID(), taskID, employeeID);
        return subtask;
    }

    public void deleteSubtaskByID(Integer subtaskID) {
        projectRepository.deleteSubtaskByID(subtaskID);
    }

    public void populateListOfTasksForProject(Project project) {
        List<Task> tasks = projectRepository.getTasksByProjectID(project.getProjectID());
        project.setTasks(tasks);
    }

    public void populateListOfSubtasksForTask(Task task) {
        List<Subtask> subtasks = projectRepository.getSubtasksByTaskID(task.getTaskID());
        task.setSubtasks(subtasks);
    }

    public void populateListOfAssignedEmployeesOfProject(Project project) {
        List<Employee> employees = projectRepository.getEmployeesByProjectID(project.getProjectID());
        project.setAssignedEmployees(employees);
    }

    public void populateListOfAssignedEmployeesOfTask(Task task) {
        List<Employee> employees = projectRepository.getEmployeesByTaskID(task.getTaskID());
        task.setAssignedEmployees(employees);
    }

    public void editProject(Project project, List<Integer> assignedEmployeeIDs) {

        //Henter ID på det projekt, der skal opdateres
        Integer projectID = project.getProjectID();

        //Henter det nuværende projekt, udelukkende så vi kan få timeslotID
        Project currentProject = projectRepository.getProjectByID(projectID);
        Integer timeslotID = currentProject.getTimeslot().getTimeslotID();

        //Laver ny beregning på plannedDays, baseret på de nye planned start date og planned finish date
        int plannedDays = calculatePlannedDays(project.getTimeslot().getPlannedStartDate(),
                project.getTimeslot().getPlannedFinishDate());

        //Opdaterer timeslotID
        projectRepository.editTimeslot(timeslotID, plannedDays, project.getTimeslot().getPlannedStartDate(),
                project.getTimeslot().getPlannedFinishDate());

        //Opdaterer navn, beskrivelse og timeslot
        //Gøres ved at oprette et nyt projekt, som kun tager de felter vi må redigere i
        Project newProject = new Project();
        newProject.setProjectName(project.getProjectName());
        newProject.setProjectDescription(project.getProjectDescription());
        newProject.setTimeslot(projectRepository.getTimeslotByID(timeslotID));

        //Opdaterer projekt-tabellen i databasen
        projectRepository.editProject(newProject, projectID);

        //Henter listen med alle nuværende employees
        List<Employee> currentEmployees = projectRepository.getEmployeesByProjectID(projectID);

        //konverterer listen til ID'er frem for objekter
        List<Integer> currentEmployeeIDs = new ArrayList<>();
        for (Employee e : currentEmployees) {
            currentEmployeeIDs.add(e.getEmployeeID());
        }

        //tilføjer kun nye employees, når de vælges på checklisten
        List<Integer> employeesToAdd = new ArrayList<>();
        for (Integer eID : assignedEmployeeIDs) {
            if (!currentEmployeeIDs.contains(eID)) {
                employeesToAdd.add(eID);
            }
        }

        //Tilføjer kun nye employees til projektet, hvis der er nye at tilføje
        //Undgår duplicates i databasen, ved brug af isEmpty()
        if (!employeesToAdd.isEmpty()) {
            projectRepository.assignEmployeesToProject(projectID, employeesToAdd);
        }

        //Fjerner kun de assigned employees, som er fravalgt i checklisten
        List<Integer> employeesToRemove = new ArrayList<>();
        for (Integer eID : currentEmployeeIDs) {
            if (!assignedEmployeeIDs.contains(eID)) {
                employeesToRemove.add(eID);
            }
        }

        for (Integer removeID : employeesToRemove) {
            projectRepository.removeEmployeeFromProject(projectID, removeID);
        }
    }

    public void calculateDifferenceInDays(Timeslot timeslot){
       long differenceInDays = timeslot.getActualFinishDate().getTime() - timeslot.getPlannedFinishDate().getTime();

       timeslot.setDifferenceInDays((int) (differenceInDays / (1000 * 60 * 60 * 24)));
    }

    public void calculateTaskWorkhours(Task task){

    }

    //Lægger total workhours fra subtask til summen af total workhours på subtasks
    public void calculateSubtaskWorkhours(Subtask subtask) {
        Integer totalWorkhoursTask = subtask.getTask().getTimeslot().getTotalWorkhours();

        Integer totalWorkhoursSubtask = subtask.getTimeslot().getTotalWorkhours();

        subtask.getTask().getTimeslot().setTotalWorkhours(totalWorkhoursTask + totalWorkhoursSubtask);

        subtask.getTask().getProject().getTimeslot().setTotalWorkhours(totalWorkhoursTask + totalWorkhoursSubtask);

        projectRepository.updateTotalWorkhoursForTimeslot(subtask.getTask().getTimeslot(),
                subtask.getTask().getTimeslot().getTimeslotID());

        projectRepository.updateTotalWorkhoursForTimeslot(subtask.getTask().getProject().getTimeslot(),
                subtask.getTask().getProject().getTimeslot().getTimeslotID());
    }

    public void finalizeSubtask(Subtask subtask){

        calculateSubtaskWorkhours(subtask);

        subtask.getTimeslot().setActualFinishDate(Date.valueOf(LocalDate.now()));
        calculateDifferenceInDays(subtask.getTimeslot());

        subtask.getTimeslot().setDone(true);

        projectRepository.archiveSubtask(subtask);

        projectRepository.finalizeTimeslot(subtask.getTimeslot(), subtask.getTimeslot().getTimeslotID());

        projectRepository.deleteSubtaskByID(subtask.getSubtaskID());
    }

    public void finalizeTask(Task task){

    }

    public void finalizeProject(Project project){

    }
}