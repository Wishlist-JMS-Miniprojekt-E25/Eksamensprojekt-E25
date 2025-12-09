package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.controller.ProjectController;
import org.example.eksamensprojekte25.model.*;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;
    @MockitoBean
    private EmployeeService employeeService;


    //tester showsAllProjects metoden
    @Test
    void shouldShowAllProjects() throws Exception {
        //fake session ID
        int employeeID = 1;

        //fake employee
        Employee manager = new Employee();
        manager.setEmployeeName("Manager Name");

        //fake projekt
        Project assignedToProject = new Project();
        assignedToProject.setProjectManager(manager);
        //fake timeslot
        Timeslot timeslot = new Timeslot();
        timeslot.setPlannedDays(0);
        assignedToProject.setTimeslot(timeslot);

        //simulerer service metode kaldende med vores fake data
        when(employeeService.getEmployeeByID(employeeID)).thenReturn(manager);
        when(employeeService.getProjectsByEmployeeID(employeeID)).thenReturn(List.of(assignedToProject));

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/userProjects").sessionAttr("employeeID", employeeID))
                .andExpect(status().isOk())
                .andExpect(view().name("showsAllProjects"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "loggedInEmployee",
                        "assignedToProjects"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("loggedInEmployee", manager))
                .andExpect(model().attribute("assignedToProjects", List.of(assignedToProject)));
    }

    //tester showsProject metoden
    @Test
    void shouldShowProject() throws Exception {
        //fake projekt id
        int projectID = 69;

        //fake manager
        Employee manager = new Employee();
        manager.setEmployeeName("manager name");
        manager.setEmployeeID(3);
        //fake loggedIn employee
        Employee employee = new Employee();
        employee.setEmployeeID(1);

        //fake projekt
        Project project = new Project();
        project.setProjectManager(manager);
        project.setAssignedEmployees(new ArrayList<>());
        project.setTasks(new ArrayList<>());
        //fake timeslot
        Timeslot timeslot = new Timeslot();
        timeslot.setPlannedDays(0);
        project.setTimeslot(timeslot);


        //simulerer service metode kaldende med vores fake data
        when(employeeService.getEmployeeByID(1)).thenReturn(employee);
        when(projectService.getProjectByID(projectID)).thenReturn(project);


        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/project/{projectID}", projectID).sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showsProject"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "project",
                        "loggedInEmployee"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("loggedInEmployee", employee));
    }

    //tester showsTask metoden
    @Test
    void shouldShowTask() throws Exception {
        //fake task id
        int taskID = 69;

        //fake employee
        Employee manager = new Employee();
        manager.setEmployeeName("manager name");
        manager.setEmployeeID(5);

        //fake projekt
        Project project = new Project();
        project.setProjectManager(manager);
        //fake project timeslot
        Timeslot projectTimeslot = new Timeslot();
        projectTimeslot.setPlannedDays(0);
        project.setTimeslot(projectTimeslot);

        //fake task
        Task task = new Task();
        task.setProject(project);
        task.setAssignedEmployees(new ArrayList<>());
        task.setSubtasks(new ArrayList<>());
        //fake task timeslot
        Timeslot taskTimeslot = new Timeslot();
        taskTimeslot.setPlannedDays(0);
        task.setTimeslot(taskTimeslot);

        //simulerer service metode kaldende med vores fake data
        when(employeeService.getEmployeeByID(5)).thenReturn(manager);
        when(projectService.getTaskByID(taskID)).thenReturn(task);

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/task/{taskID}", taskID).sessionAttr("employeeID", 5))
                .andExpect(status().isOk())
                .andExpect(view().name("showsTask"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "task",
                        "loggedInEmployee"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("task", task))
                .andExpect(model().attribute("loggedInEmployee", manager));
    }

    @Test
    void shouldAddProject() throws Exception {

        Employee employee = new Employee();

        when(employeeService.getAllEmployees()).thenReturn(List.of(employee));

        mockMvc.perform(get("/addProject"))
                .andExpect(status().isOk())
                .andExpect(view().name("addProject"))
                .andExpect(model().attributeExists(
                        "project",
                        "allEmployees"))
                .andExpect(model().attribute("project", instanceOf(Project.class)))
                .andExpect(model().attribute("allEmployees", List.of(employee)));
    }

    @Test
    void shouldSaveProject() throws Exception {

        mockMvc.perform(post("/saveProject")
                        .sessionAttr("employeeID", 1)
                        .param("projectName", "Test Projekt")
                        .param("ProjectDescription", "Beskrivelse")
                        .param("plannedStartDate", "2025-11-01")
                        .param("plannedFinishDate", "2025-12-01")
                        .param("assignedEmployeeIDs", "2", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/userProjects"));

        ArgumentCaptor<Integer> managerCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> finishDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<List> assignedEmployeesCaptor = ArgumentCaptor.forClass(List.class);

        verify(projectService).addProject(
                managerCaptor.capture(),
                nameCaptor.capture(),
                descriptionCaptor.capture(),
                startDateCaptor.capture(),
                finishDateCaptor.capture(),
                assignedEmployeesCaptor.capture()
        );

        assertEquals(1, managerCaptor.getValue());
        assertEquals("Test Projekt", nameCaptor.getValue());
        assertEquals("Beskrivelse", descriptionCaptor.getValue());
        assertEquals(Date.valueOf("2025-11-01"), startDateCaptor.getValue());
        assertEquals(Date.valueOf("2025-12-01"), finishDateCaptor.getValue());
        assertEquals(List.of(2, 3), assignedEmployeesCaptor.getValue());
    }

    @Test
    void shouldDeleteProject() throws Exception {
        mockMvc.perform(post("/deleteProject/{projectID}", 3))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/userProjects"));

        verify(projectService, times(1)).deleteProjectByID(3);
    }

    @Test
    void shouldShowAddTaskForm() throws Exception {

        Project project = new Project();
        Integer projectID = 5;

        Employee employee = new Employee();

        when(projectService.getProjectByID(projectID))
                .thenReturn(project);

        mockMvc.perform(get("/addTask/{projectID}", projectID))
                .andExpect(status().isOk())
                .andExpect(view().name("addTask"))
                .andExpect(model().attributeExists("task"))
                .andExpect(model().attribute("task", instanceOf(Task.class)));
    }

    @Test
    void shouldSaveTask() throws Exception {

        Project project = new Project();
        project.setProjectID(7);

        when(projectService.getProjectByID(7)).thenReturn(project);

        mockMvc.perform(post("/saveTask")
                        .param("projectID", "7")
                        .param("taskName", "Nyt task")
                        .param("taskDescription", "En beskrivelse")
                        .param("plannedStartDate", "2025-02-01")
                        .param("plannedFinishDate", "2025-02-20")
                        .param("assignedEmployeeIDs", "3", "4"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/" + 7));

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> descCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> finishDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Integer> projectIDCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<List> employeesCaptor = ArgumentCaptor.forClass(List.class);

        verify(projectService).addTask(
                nameCaptor.capture(),
                descCaptor.capture(),
                startDateCaptor.capture(),
                finishDateCaptor.capture(),
                projectIDCaptor.capture(),
                employeesCaptor.capture()
        );

        assertEquals("Nyt task", nameCaptor.getValue());
        assertEquals("En beskrivelse", descCaptor.getValue());
        assertEquals(Date.valueOf("2025-02-01"), startDateCaptor.getValue());
        assertEquals(Date.valueOf("2025-02-20"), finishDateCaptor.getValue());
        assertEquals(7, projectIDCaptor.getValue());
        assertEquals(List.of(3, 4), employeesCaptor.getValue());
    }

    @Test
    void shouldDeleteTask() throws Exception {

        Project project = new Project();
        project.setProjectID(10);

        Task task = new Task();
        task.setTaskID(1);
        task.setProject(project);

        when(projectService.getTaskByID(1)).thenReturn(task);
        doNothing().when(projectService).deleteTaskByID(1);

        mockMvc.perform(post("/deleteTask/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/10"));
    }

    @Test
    void shouldDeleteSubtask() throws Exception {

        Task task = new Task();
        task.setTaskID(1);

        Subtask subtask = new Subtask();
        subtask.setSubtaskID(3);
        subtask.setTask(task);

        when(projectService.getSubtaskByID(3)).thenReturn(subtask);
        doNothing().when(projectService).deleteSubtaskByID(1);

        mockMvc.perform(post("/deleteSubtask/{subtaskID}", 3))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/task/" + 1));

        verify(projectService, times(1)).deleteSubtaskByID(3);
    }

    @Test
    void shouldShowAddSubtaskForm() throws Exception {

        Task task = new Task();
        Integer taskID = 1;

        when(projectService.getTaskByID(taskID))
                .thenReturn(task);

        mockMvc.perform(get("/task/{taskID}/addSubtask", taskID))
                .andExpect(status().isOk())
                .andExpect(view().name("addSubtask"))
                .andExpect(model().attributeExists("subtask"))
                .andExpect(model().attribute("subtask", instanceOf(Subtask.class)));
    }

    @Test
    void shouldSaveSubtask() throws Exception {

        Task task = new Task();
        task.setTaskID(1);

        when(projectService.getTaskByID(1)).thenReturn(task);

        mockMvc.perform(post("/task/saveSubtask")
                        .param("taskID", "1")
                        .param("subtaskName", "Ny subtask")
                        .param("subtaskDescription", "En beskrivelse")
                        .param("plannedStartDate", "2025-02-01")
                        .param("plannedFinishDate", "2025-02-20")
                        .param("assignedEmployeeID", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/task/1"));

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> taskIDCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> assignedEmployeeIDCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> finishDateCaptor = ArgumentCaptor.forClass(Date.class);

        verify(projectService).addSubtask(
                nameCaptor.capture(),
                descriptionCaptor.capture(),
                taskIDCaptor.capture(),
                assignedEmployeeIDCaptor.capture(),
                startDateCaptor.capture(),
                finishDateCaptor.capture()
        );

        assertEquals("Ny subtask", nameCaptor.getValue());
        assertEquals("En beskrivelse", descriptionCaptor.getValue());
        assertEquals(1, taskIDCaptor.getValue());
        assertEquals(3, assignedEmployeeIDCaptor.getValue());
        assertEquals(Date.valueOf("2025-02-01"), startDateCaptor.getValue());
        assertEquals(Date.valueOf("2025-02-20"), finishDateCaptor.getValue());
    }
}