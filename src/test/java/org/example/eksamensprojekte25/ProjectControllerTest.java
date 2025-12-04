package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.controller.ProjectController;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @MockitoBean
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    @Test
    void showExpectedProjects() throws Exception {

        //Tester om forsiden virker
        mockMvc.perform(get("/projects/{employeeID}", 1)
                .sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showAllProjectsByEmployeeID"));
    }

    @Test
    void showAllProjectsByEmployeeID() throws Exception {

        // Tester om forsiden virker for det specifikke employeeID og employees projekter
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Hans", "hans", "123", false));
        employees.add(new Employee(2, "Frede", "frede", "123", true));

        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, 1, "Byg hus", "Bygge et stort hus", 1, employees));
        projects.add(new Project(2, 2, "Lav ønskeliste", "Lav et dejligt stort ønskeliste", 2, employees));

        List<Timeslot> timeslots = new ArrayList<>();
        timeslots.add(new Timeslot(1, 30,
                Date.valueOf("2025-11-25"),
                Date.valueOf("2025-12-25"),
                Date.valueOf("2026-01-01"),
                30, 30, false));

        timeslots.add(new Timeslot(2, 30,
                Date.valueOf("2025-01-25"),
                Date.valueOf("2025-03-25"),
                Date.valueOf("2026-01-02"),
                300, 1600, true));

        // Mock service-kaldene så controlleren får data
        when(service.getProjectsByEmployeeID(1)).thenReturn(projects);
        when(service.getAllTimeslots()).thenReturn(timeslots);

        mockMvc.perform(get("/projects/{employeeID}", 1)
                        .sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showAllProjectsByEmployeeID"))
                .andExpect(model().attribute("projects", projects))
                .andExpect(model().attribute("timeslots", timeslots));
    }

    @Test
    void shouldAddProject() throws Exception{

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Simon", "Sim", "123", true));
        employees.add(new Employee(2, "Martin", "Mar", "abc", false));

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/addProject")
                        .sessionAttr("employeeID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("addProject"))
                .andExpect(model().attribute("projectManager", 1))
                .andExpect(model().attribute("allEmployees", employees));
    }

    @Test
    void shouldSaveProject() throws Exception{

        mockMvc.perform(post("/saveProject")
                .sessionAttr("employeeID", 1)
                .param("projectName", "Test Projekt")
                .param("ProjectDescription", "Beskrivelse")
                .param("plannedStartDate", "2025-11-01")
                .param("plannedFinishDate", "2025-12-01")
                .param("assignedEmployeeIDs", "2", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/projects/1"));

        ArgumentCaptor<Integer> managerCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<Date> finishDateCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<List> assignedEmployeesCaptor = ArgumentCaptor.forClass(List.class);

        verify(service).addProject(
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
    void shouldDeleteProject() throws Exception{
        mockMvc.perform(post("/deleteProject/{projectID}", 3)
                .sessionAttr("employeeID", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/projects"));

        verify(projectService, times(1)).deleteProjectByID(3);
    }
}
