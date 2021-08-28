package com.example.lungi.repository;

import com.example.lungi.model.ConeBheem;
import com.example.lungi.model.CreditDebit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditDebitRepository extends JpaRepository<CreditDebit, Long> {
    @Query("select p from CreditDebit as p where employee_id = :id order by p.date desc ")
    List<CreditDebit> findByEmployeeId(@Param("id") Long id);
}
