package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.model.Project;
import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.example.eksamensprojekte25.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    //henter en employee baseret på employee userName og userPassword
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee findEmployeeByCredentials(String userName, String userPassword) {
        return employeeRepository.findEmployeeByCredentials(userName, userPassword);
    }

    //henter en employee baseret på employee id
    public Employee getEmployeeByID(Integer employeeID) {
        Employee employee = employeeRepository.getEmployeeByID(employeeID);
        populateListOfManagedProjects(employee);
        for(Project project : employee.getManagedProjects()) {
            populateListOfAssignedEmployees(project);
        }
        return employee;
    }

    public void populateListOfManagedProjects(Employee employee) {
        List<Project> managedProjects = employeeRepository.getProjectsByManagerID(employee.getEmployeeID());
        employee.setManagedProjects(managedProjects);
    }

    public void populateListOfAssignedEmployees(Project project) {
        List<Employee> employees = projectRepository.getEmployeesByProjectID(project.getProjectID());
        project.setAssignedEmployees(employees);
    }

    //Henter alle projekter, som man er assigned til baseret på employee id
    public List<Project> getProjectsByEmployeeID(Integer employeeID) {
        List<Project> projects = employeeRepository.getProjectsByEmployeeID(employeeID);
        for (Project project : projects) {
            populateListOfAssignedEmployees(project);
        }
        return projects;
    }

    public List<Project> getProjectsByManagerID(Integer managerID) {
        return employeeRepository.getProjectsByManagerID(managerID);
    }

}
