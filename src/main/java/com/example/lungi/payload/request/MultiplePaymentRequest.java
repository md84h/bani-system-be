package com.example.lungi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiplePaymentRequest {
    private List<PaymentDetails> list;
    private Date paymentDate;
    private String comment;
}
