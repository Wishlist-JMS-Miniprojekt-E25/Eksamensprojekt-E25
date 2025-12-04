package org.example.eksamensprojekte25.controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService){
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    //viser forside
    @GetMapping()
    public String Frontpage() {
        return "Frontpage";
    }

    //viser login siden
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    //går til bruger forsiden fra login siden hvis succefuldt login, ellers bliver du på login siden og må prøve igen
    @PostMapping("/postLogin")
    public String login(@RequestParam String userName,
                        @RequestParam String userPassword,
                        HttpSession session,
                        Model model) {

        Employee loggedInEmployee = employeeService.findEmployeeByCredentials(userName, userPassword);
        if (loggedInEmployee != null) {
            // Gem login-info i session
            session.setAttribute("employeeID", loggedInEmployee.getEmployeeID());
            return "redirect:/userProjects";
        } else {
            model.addAttribute("error", true);
            return "login"; // viser login igen med fejl
        }
    }

    //slutter session og går tilbage til login siden
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
