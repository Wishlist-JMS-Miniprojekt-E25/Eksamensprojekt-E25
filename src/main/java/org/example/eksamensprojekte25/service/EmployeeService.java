package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee findEmployeeByCredentials(String userName, String userPassword){
        return employeeRepository.findEmployeeByCredentials(userName, userPassword);
    }

    public Employee getEmployeeByID(Integer employeeID) {
        return employeeRepository.getEmployeeByID(employeeID);
    }

}
