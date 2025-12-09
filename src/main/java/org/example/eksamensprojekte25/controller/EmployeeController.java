package org.example.eksamensprojekte25.controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.service.EmployeeService;
import org.example.eksamensprojekte25.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        return "loginPage";
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
            return "redirect:/userOptions";
        } else {
            model.addAttribute("error", true);
            return "loginPage"; // viser login igen med fejl
        }
    }

    //slutter session og går tilbage til login siden
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(HttpSession session, Model model) {
        Integer currentEmployeeID = (Integer) session.getAttribute("employeeID");

        model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {

        try {
            // Forsøger at oprette employee. Kaster IllegalArgumentException hvis username allerede findes.
            employeeService.addEmployee(
                    employee.getEmployeeName(),
                    employee.getUserName(),
                    employee.getUserPassword()
            );

        } catch (IllegalArgumentException e) {

            // addFlashAttribute gemmer data KUN til næste request efter redirect.
            // Det bruges her fordi redirect laver en NY HTTP-request, og almindelige model-attributter ellers ville gå tabt.
            // FlashAttributes sikrer at fejlbesked + de indtastede værdier kan vises igen på createEmployee-siden.
            redirectAttributes.addFlashAttribute("errorMessage", "Brugernavnet findes allerede – prøv et andet.");
            redirectAttributes.addFlashAttribute("employeeName", employee.getEmployeeName());
            redirectAttributes.addFlashAttribute("userName", employee.getUserName());
            redirectAttributes.addFlashAttribute("userPassword", employee.getUserPassword());

            // Sender brugeren tilbage til formularen, nu med fejlbesked og deres tidligere input bevaret.
            return "redirect:/createEmployee";
        }

        // Hvis alt lykkes, sendes brugeren videre til forsiden.
        return "redirect:/userOptions";
    }


}