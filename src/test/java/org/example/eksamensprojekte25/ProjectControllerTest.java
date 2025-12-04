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
        employee.setEmployeeID(employeeID);

        //fake projekter
        Project managedProject = new Project();
        managedProject.setProjectID(2);
        Project assignedToProject = new Project();
        assignedToProject.setProjectID(3);

        //fake timeslot
        Timeslot timeslot = new Timeslot();
        timeslot.setTimeslotID(4);

        //simulerer service metode kaldende med vores fake data
        when(employeeService.getEmployeeByID(employeeID)).thenReturn(employee);
        when(projectService.getProjectsByManagerID(employeeID)).thenReturn(List.of(managedProject));
        when(projectService.getProjectsByEmployeeID(employeeID)).thenReturn(List.of(assignedToProject));
        when(projectService.getAllTimeslots()).thenReturn(List.of(timeslot));

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sender de rigtige værdier over
        mockMvc.perform(get("/userProjects").sessionAttr("employeeID", employeeID))
                .andExpect(status().isOk())
                .andExpect(view().name("showsAllProjects"))
                //eksisterende attributter(nøglerne), som vi sender over til html siden fra controller metoden
                .andExpect(model().attributeExists(
                        "employee",
                        "projectsYouManage",
                        "assignedToProjects",
                        "timeslots"
                ))
                //vi giver attributterne vores fake værdier
                .andExpect(model().attribute("employee", employee))
                .andExpect(model().attribute("projectsYouManage", List.of(managedProject)))
                .andExpect(model().attribute("assignedToProjects", List.of(assignedToProject)))
                .andExpect(model().attribute("timeslots", List.of(timeslot)));
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
    void shouldDeleteProject() throws Exception{
        mockMvc.perform(post("/deleteProject/{projectID}", 3)
                .sessionAttr("employeeID", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/projects/" + 1));

        verify(projectService, times(1)).deleteProjectByID(3);
    }
}
