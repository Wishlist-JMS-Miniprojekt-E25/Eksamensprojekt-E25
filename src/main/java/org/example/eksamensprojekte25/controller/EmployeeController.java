package org.example.eksamensprojekte25.controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService){
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "Login";
    }

    //går til brugerforsiden fra forsiden efter succefuldt login
    @PostMapping("/postLogin")
    public String login(@RequestParam String userName,
                        @RequestParam String userPassword,
                        HttpSession session,
                        Model model, RedirectAttributes redirectAttributes) {

        Employee loggedInEmployee = employeeService.findEmployeeByCredentials(userName, userPassword);

        if (loggedInEmployee != null) {
            // Gem login-info i session
            session.setAttribute("employeeID", loggedInEmployee.getEmployeeID());

            // Viser showAllProjectsByEmployeeID efter login
            redirectAttributes.addAttribute("employeeID", loggedInEmployee.getEmployeeID());
            return "redirect:/projects/{employeeID}";
        } else {
            model.addAttribute("error", true);
            return "Login"; // viser login igen med fejl
        }
    }

}
