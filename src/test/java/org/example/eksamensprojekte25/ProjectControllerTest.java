package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.controller.ProjectController;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.service.ProjectService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @Test
    void showExpectedProjects() throws Exception {

        //Tester om forsiden virker
        mockMvc.perform(get("/projects/{employeeID}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showAllProjects"));
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

        mockMvc.perform(get("/projects/{employeeID}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("showAllProjects"))
                .andExpect(model().attribute("projects", projects))
                .andExpect(model().attribute("timeslots", timeslots));
    }
}
