package com.example.lungi.repository;

import com.example.lungi.model.ConeBheem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConeBheemRepository extends JpaRepository<ConeBheem, Long> {
    @Query("select p from ConeBheem as p where employee_id = :id order by p.date desc ")
    List<ConeBheem> findByEmployeeId(@Param("id") Long id);

    @Query("select p from ConeBheem as p where employee_id = :id and product = :product and type = :type order by p.date desc ")
    List<ConeBheem> findByEmployeeIdByProductByType(@Param("id") Long id, @Param("product") String product, @Param("type") String type);
}
