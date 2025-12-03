package org.example.eksamensprojekte25.service;


import org.example.eksamensprojekte25.model.Employee;
import org.example.eksamensprojekte25.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    public Employee findEmployeeByCredentials(String userName, String userPassword){
        return employeeRepository.findEmployeeByCredentials(userName, userPassword);
    }


}
