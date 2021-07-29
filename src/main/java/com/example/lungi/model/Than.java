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
@Table(name = "than")
public class Than {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product")
    private String product;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "date")
    private Date date;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "extra_lungi")
    private Long extraLungi;

    @Column(name = "computed_amount")
    private Double computedAmount;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private String comment;
}
