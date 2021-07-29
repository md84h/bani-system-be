package com.example.lungi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Long id;
    private Double computedAmount;
    private Double totalAmount;
    private Date paymentDate;
    private String status;
    private String comment;
}
