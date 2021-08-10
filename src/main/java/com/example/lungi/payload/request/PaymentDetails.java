package com.example.lungi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    private Long id;
    private Double computedAmount;
    private Double totalAmount;

}
