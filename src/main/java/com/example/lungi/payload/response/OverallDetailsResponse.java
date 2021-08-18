package com.example.lungi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverallDetailsResponse {
    public String name;

    public Long totalThan;

    public Long extraLungi;

    public Long totalLungi;

    public Long totalLungiBheem;

    public Double totalLungiBheemAmount;

    public Long totalLungiCone;

    public Double totalLungiConeWeight;

    public Double totalLungiBheemLength;

    public Double totalLungiBheemNetWeight;

    public Long totalLungiCutPiece;

    public Double totalLungiCutPieceAmount;

    public Double totalLungiAmount;

    public Double paidLungiAmount;

    public Double pendingLungiAmount;

    public Long totalChauka;

    public Long totalChaukaCone;

    public Double totalChaukaConeWeight;

    public Long totalChaukaBheem;

    public Double totalChaukaBheemLength;

    public Double totalChaukaBheemNetWeight;

    public Long totalChaukaCutPiece;

    public Double totalChaukaCutPieceAmount;

    public Double totalChaukaAmount;

    public Double paidChaukaAmount;

    public Double pendingChaukaAmount;

    public Double totalAmount;

    public Double totalPaidAmount;

    public Double totalPendingAmount;

    public Double totalCutPieceAmount;

    public Date startDate;

    public Date endDate;

    public Double totalBheemAmount;
}
