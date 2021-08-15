package com.example.lungi.service;

import com.example.lungi.exception.EmployeeAlreadyExistException;
import com.example.lungi.exception.EmployeeNotFoundException;
import com.example.lungi.model.Employee;
import com.example.lungi.repository.EmployeeRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    public List<Employee> getEmployeeList() {
        return repository.getByOrder();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            return employee;
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public Employee createNewEmployee(Employee employee) {
        List<Employee> employeeList = repository.findAll();
        Boolean alreadyExist = false;
        for (Employee emp: employeeList) {
            if (emp.getName().equalsIgnoreCase(employee.getName())) {
                alreadyExist = true;
                break;
            }
        }
        if (alreadyExist) {
            throw new EmployeeAlreadyExistException(employee.getName());
        } else {
            employee.setOrder(100);
            return repository.save(employee);
        }
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            List<Employee> employeeList = repository.findAll();
            Boolean alreadyExist = false;
            for (Employee emp: employeeList) {
                if (emp.getId() != employee.getId() && emp.getName().equalsIgnoreCase(employee.getName())) {
                    alreadyExist = true;
                    break;
                }
            }
            if (alreadyExist) {
                throw new EmployeeAlreadyExistException(employee.getName());
            } else {
                return repository.save(employee);
            }
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
