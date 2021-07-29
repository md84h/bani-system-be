package com.example.lungi.controller;

import com.example.lungi.model.Employee;
import com.example.lungi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(service.getEmployeeList());
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Employee>> getEmployeeBYId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getEmployeeById(id));
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(service.createNewEmployee(employee));
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateEmployee(id, employee));
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}
