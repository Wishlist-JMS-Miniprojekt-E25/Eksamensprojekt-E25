package org.example.eksamensprojekte25;

import org.example.eksamensprojekte25.controller.EmployeeController;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;
    @MockitoBean
    private ProjectService projectService;

    @Test
    void shouldShowFrontpage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Frontpage"));
    }

    @Test
    void shouldShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"));
    }

    //tester login metoden hvis brugeren findes
    @Test
    void SuccessfulLogin() throws Exception {
        //fake employee
        Employee employee = new Employee();
        employee.setEmployeeID(69);

        //simulerer service metode kaldet med fake data
        when(employeeService.findEmployeeByCredentials("legenden", "123"))
                .thenReturn(employee);

        //Tester at controller metoden gør hvad den skal, at den får de rigtige parametre,
        //at den redirecter til det faktiske endpoint og at den setter en session attribut
        mockMvc.perform(post("/postLogin")
                        //giver parametre fake login info
                        .param("userName", "legenden")
                        .param("userPassword", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userOptions"))
                //setter session id til vores fake
                .andExpect(request().sessionAttribute("employeeID", 69));
    }

    //tester login metoden hvis brugeren ikke findes (er null)
    @Test
    void loginFails() throws Exception {
        //simulerer service metode kaldet med fake data
        when(employeeService.findEmployeeByCredentials("legenden", "wrongPassword"))
                .thenReturn(null);

        //tester at controller metoden går til login siden og viser fejlbesked hvis employee er null
        mockMvc.perform(post("/postLogin")
                        //giver parametre fake login info
                        .param("userName", "legenden")
                        .param("userPassword", "wrongPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", true));
    }

    //tester logout metoden
    @Test
    void logout_shouldEndSession_shouldRedirect() throws Exception {
        //fake session
        MockHttpSession session = new MockHttpSession();

        //tester om den redirecter til login siden
        mockMvc.perform(post("/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        //session skal være invald her, så fake session is invalid bliver sat til true
        assertTrue(session.isInvalid());
    }

    //tester saveEmployee metoden
    @Test
    void shouldSaveEmployeeSuccessfully() throws Exception {

        // Når service bliver kaldt → gør ingenting (ingen exception)
        when(employeeService.addEmployee("Simon", "sim123", "pass")).thenReturn(new Employee());

        mockMvc.perform(post("/saveEmployee")
                        .param("employeeName", "Simon")
                        .param("userName", "sim123")
                        .param("userPassword", "pass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userOptions"));

        // Verificér at service rent faktisk blev kaldt
        verify(employeeService, times(1)).addEmployee("Simon", "sim123", "pass");
    }

    @Test
    void shouldDeleteEmployee() throws Exception {

        // Kald endpointet
        mockMvc.perform(post("/deleteEmployee/{employeeID}", 5))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userOptions"))
                .andExpect(flash().attributeExists("successMessage"));

        // Verificér at servicen blev kaldt korrekt
        verify(employeeService, times(1)).deleteEmployeeByID(5);
    }

    @Test
    void shouldUpdateEmployeeSuccessfully() throws Exception {

        mockMvc.perform(post("/updateEmployee/{employeeID}", 3)
                        .param("employeeName", "New Name")
                        .param("userName", "newUser")
                        .param("userPassword", "newPW"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userOptions"))
                .andExpect(flash().attributeExists("successMessage"));

        // Capture Employee-objektet, så vi kan tjekke værdierne
        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeService).editEmployee(employeeCaptor.capture(), eq(3));

        Employee edited = employeeCaptor.getValue();
        assertEquals("New Name", edited.getEmployeeName());
        assertEquals("newUser", edited.getUserName());
        assertEquals("newPW", edited.getUserPassword());
    }

    @Test
    void shouldRedirectBackToEditForm() throws Exception {

        mockMvc.perform(post("/updateEmployee/{employeeID}", 3)
                        .param("employeeName", "New Name")
                        .param("userName", "existingUser")
                        .param("userPassword", "pw123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userOptions"));
    }



}