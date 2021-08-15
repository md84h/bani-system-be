package com.example.lungi.repository;

import com.example.lungi.model.Than;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ThanRepository extends JpaRepository<Than, Long> {
    @Query("select p from Than as p where employee_id = :id order by p.date desc")
    List<Than> findByEmployeeId(@Param("id") Long id);

    @Query("select p from Than as p where employee_id = :id and product = :product order by p.date desc")
    List<Than> findByEmployeeIdByProduct(@Param("id") Long id, @Param("product") String product);

    @Query("select p from Than as p where employee_id = :id and date between :startDate and :endDate")
    List<Than> findByEmployeeIdInDateRange(@Param("id") Long id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
