package com.example.lungi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cone_bheem")
public class ConeBheem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "product")
    private String product;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "date")
    private Date date;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "pipe_weight")
    private Double pipeWeight;

    @Column(name = "length")
    private Double length;

    @Column(name = "status")
    private String status;
}
