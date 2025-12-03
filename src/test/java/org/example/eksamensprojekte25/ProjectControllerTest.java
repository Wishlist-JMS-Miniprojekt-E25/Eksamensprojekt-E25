package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.controller.ProjectController;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.model.Timeslot;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        //simulerer service metode kaldende fra controlleren
        when(employeeService.getEmployeeByID(employeeID)).thenReturn(employee);
        when(projectService.getProjectsByManagerID(employeeID)).thenReturn(List.of(managedProject));
        when(projectService.getProjectsByEmployeeID(employeeID)).thenReturn(List.of(assignedToProject));
        when(projectService.getAllTimeslots()).thenReturn(List.of(timeslot));

        //Tester at controller metoden gør hvad den skal, at den returnere html siden,
        //hvilke model-atributter der eksisterer og at den sende de rigtige værdier over
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
}
