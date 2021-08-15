package com.example.lungi.repository;

import com.example.lungi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select p from Employee as p order by p.order asc")
    List<Employee> getByOrder();
}
