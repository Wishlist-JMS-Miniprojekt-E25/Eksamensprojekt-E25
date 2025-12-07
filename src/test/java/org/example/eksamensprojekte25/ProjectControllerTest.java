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
        Employee employee = new Employee();
        employee.setManagedProjects(new ArrayList<>());

        //fake projekt
        Project assignedToProject = new Project();
        //fake timeslot
        Timeslot timeslot = new Timeslot();
        timeslot.setPlannedDays(0);
        assignedToProject.setTimeslot(timeslot);

        //simulerer service metode kaldende med vores fake data
        when(employeeService.getEmployeeByID(employeeID)).thenReturn(employee);
        when(employeeService.getAllEmployees()).thenReturn(List.of(employee));
        when(employeeService.getProjectsByEmployeeID(employeeID)).thenReturn(List.of(assignedToProject));

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/userProjects").sessionAttr("employeeID", employeeID))
                .andExpect(status().isOk())
                .andExpect(view().name("showsAllProjects"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "loggedInEmployee",
                        "allEmployees",
                        "assignedToProjects"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("loggedInEmployee", employee))
                .andExpect(model().attribute("allEmployees", List.of(employee)))
                .andExpect(model().attribute("assignedToProjects", List.of(assignedToProject)));
    }

    //tester showsProject metoden
    @Test
    void shouldShowProject() throws Exception {
        //fake projekt id
        int projectID = 69;

        //fake projekt
        Project project = new Project();
        project.setProjectManagerID(3);
        project.setTasks(new ArrayList<>());
        //fake timeslot
        Timeslot timeslot = new Timeslot();
        timeslot.setPlannedDays(0);
        project.setTimeslot(timeslot);

        //fake employee
        Employee employee = new Employee();

        //simulerer service metode kaldende med vores fake data
        when(projectService.getProjectByID(projectID)).thenReturn(project);
        when(employeeService.getEmployeeByID(3)).thenReturn(employee);

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/project/{projectID}", projectID).sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showsProject"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "project",
                        "manager",
                        "loggedInEmployee"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("manager", employee))
                .andExpect(model().attribute("loggedInEmployee", 1));
    }

    //tester showsTask metoden
    @Test
    void shouldShowTask() throws Exception {
        //fake task id
        int taskID = 69;

        //fake projekt
        Project project = new Project();
        project.setProjectManagerID(5);
        //fake projekt timeslot
        Timeslot projectTimeslot = new Timeslot();
        projectTimeslot.setPlannedDays(0);
        project.setTimeslot(projectTimeslot);

        //fake task
        Task task = new Task();
        task.setSubtasks(new ArrayList<>());
        //fake task timeslot
        Timeslot taskTimeslot = new Timeslot();
        taskTimeslot.setPlannedDays(0);
        task.setTimeslot(taskTimeslot);

        //fake employee
        Employee employee = new Employee();

        //simulerer service metode kaldende med vores fake data
        when(projectService.getProjectByTaskID(taskID)).thenReturn(project);
        when(employeeService.getEmployeeByID(5)).thenReturn(employee);
        when(projectService.getTaskByID(taskID)).thenReturn(task);

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/task/{taskID}", taskID).sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showsTask"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "project",
                        "manager",
                        "task",
                        "loggedInEmployee"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("manager", employee))
                .andExpect(model().attribute("task", task))
                .andExpect(model().attribute("loggedInEmployee", 1));
    }

    @Test
    void shouldAddProject() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Simon", "Sim", "123"));
        employees.add(new Employee(2, "Martin", "Mar", "abc"));

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/addProject")
                        .sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("addProject"))
                .andExpect(model().attribute("projectManager", 1))
                .andExpect(model().attribute("allEmployees", employees));
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
        mockMvc.perform(post("/deleteProject/{projectID}", 3)
                        .sessionAttr("employeeID", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/userProjects"));

        verify(projectService, times(1)).deleteProjectByID(3);
    }

    @Test
    void shouldShowAddTaskForm() throws Exception {

        Integer projectID = 5;

        Employee employee = new Employee();

        Task task = new Task();
        task.setProjectID(projectID);

        when(projectService.getEmployeesByProjectID(projectID))
                .thenReturn(List.of(employee));

        mockMvc.perform(get("/addTask/{projectID}", projectID))
                .andExpect(status().isOk())
                .andExpect(view().name("addTask"))
                .andExpect(model().attributeExists(
                        "task",
                        "projectEmployees"))
                .andExpect(model().attribute("task", samePropertyValuesAs(task)))
                .andExpect(model().attribute("projectEmployees", List.of(employee)));
    }

    @Test
    void shouldSaveTask() throws Exception {

        mockMvc.perform(post("/saveTask")
                        .sessionAttr("employeeID", 1)
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

        Task task = new Task();
        task.setTaskID(1);
        task.setProjectID(10);

        when(projectService.getTaskByID(1)).thenReturn(task);

        mockMvc.perform(post("/deleteTask/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/10"));
    }

    @Test
    void shouldDeleteSubtask() throws Exception {

        mockMvc.perform(post("/task/{taskID}/deleteSubtask/{subtaskID}", 1, 3))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/task/" + 1));

        verify(projectService, times(1)).deleteSubtaskByID(3);
    }

    @Test
    void shouldShowAddSubtaskForm() throws Exception {
        Integer taskID = 1;

        List<Employee> taskEmployees = List.of(
                new Employee(1, "Hans", "h", "123"),
                new Employee(2, "Frede", "f", "321")
        );

        when(projectService.getEmployeesByTaskID(taskID))
                .thenReturn(taskEmployees);

        mockMvc.perform(get("/task/{taskID}/addSubtask", taskID))
                .andExpect(status().isOk())
                .andExpect(view().name("addSubtask"))
                .andExpect(model().attributeExists("subtask"))
                .andExpect(model().attribute("taskEmployees", taskEmployees));
    }

    @Test
    void shouldSaveSubtask() throws Exception {

        mockMvc.perform(post("/task/{taskID}/saveSubtask", 1)
                        .param("taskID", "1")
                        .param("subtaskName", "Ny subtask")
                        .param("subtaskDescription", "En beskrivelse")
                        .param("plannedStartDate", "2025-02-01")
                        .param("plannedFinishDate", "2025-02-20")
                        .param("employeeID", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/task/1"));

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> taskIDCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> employeeIDCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> finishDateCaptor = ArgumentCaptor.forClass(Date.class);

        verify(projectService).addSubtask(
                nameCaptor.capture(),
                descriptionCaptor.capture(),
                taskIDCaptor.capture(),
                employeeIDCaptor.capture(),
                startDateCaptor.capture(),
                finishDateCaptor.capture()
        );

        assertEquals("Ny subtask", nameCaptor.getValue());
        assertEquals("En beskrivelse", descriptionCaptor.getValue());
        assertEquals(1, taskIDCaptor.getValue());
        assertEquals(3, employeeIDCaptor.getValue());
        assertEquals(Date.valueOf("2025-02-01"), startDateCaptor.getValue());
        assertEquals(Date.valueOf("2025-02-20"), finishDateCaptor.getValue());
    }
}