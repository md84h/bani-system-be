package com.example.lungi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverallDetailsRequest {
    public Long employeeId;

    public Date startDate;

    public Date endDate;

}
