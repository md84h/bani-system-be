package com.example.lungi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConeBheemResponse {
    public long id;

    public String type;

    public String product;

    public Long employeeId;

    public Date date;

    public Double weight;

    public int quantity;

    public Double pipeWeight;

    public Double length;

    public String status;

    public Double netWeight;
}
