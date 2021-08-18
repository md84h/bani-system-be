package com.example.lungi.service;

import com.example.lungi.model.ConeBheem;
import com.example.lungi.model.Than;
import com.example.lungi.payload.request.OverallDetailsRequest;
import com.example.lungi.payload.response.OverallDetailsResponse;
import com.example.lungi.repository.ConeBheemRepository;
import com.example.lungi.repository.EmployeeRepository;
import com.example.lungi.repository.ThanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OverallDetailsService {
    @Autowired
    ConeBheemRepository coneBheemRepository;

    @Autowired
    ThanRepository thanRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public OverallDetailsResponse getOverallDetails(OverallDetailsRequest request) {
        Long id = request.getEmployeeId();
        Date startDate = request.getStartDate();
        Date endDate = request.getEndDate();
        List<Than> thanList = thanRepository.findByEmployeeIdInDateRange(id, startDate, endDate);
        List<ConeBheem> coneBheemList = coneBheemRepository.findByEmployeeIdInDateRange(id, startDate, endDate);
        String name = employeeRepository.getById(id).getName();
        Long totalThan = new Long(0);
        Long totalChauka = new Long(0);
        Long extraLungi = new Long(0);
        Double paidLungiAmount = 0.0;
        Double paidChaukaAmount = 0.0;
        Long totalLungiCutPiece = new Long(0);
        Long totalChaukaCutPiece = new Long(0);
        for (Than than: thanList) {
            if (than.getProduct().equals("LUNGI")) {
                totalThan += than.getQuantity();
                if (than.getTotalAmount() != null) {
                    paidLungiAmount += than.getTotalAmount();
                }

                if (than.getCutPiece() != null) {
                    totalLungiCutPiece += than.getCutPiece();
                }
            } else {
                totalChauka += than.getQuantity();
                if (than.getTotalAmount() != null) {
                    paidChaukaAmount += than.getTotalAmount();
                }
                if (than.getCutPiece() != null) {
                    totalChaukaCutPiece += than.getCutPiece();
                }
            }
            if (than.getExtraLungi() != null) {
                extraLungi += than.getExtraLungi();
            }
        }
        Long totalLungi = totalThan * 11 + extraLungi;
        Double totalLungiAmount = totalLungi * 14.0;
        Double totalLungiCutPieceAmount = totalLungiCutPiece * 50.0;
        Double totalChaukaAmount = totalChauka * 19.0;
        Double totalChaukaCutPieceAmount = totalChaukaCutPiece * 70.0;
        Double pendingChaukaAmount = totalChaukaAmount - paidChaukaAmount - totalChaukaCutPieceAmount;
        Double totalAmount = totalLungiAmount + totalChaukaAmount;
        Double totalPaidAmount = paidLungiAmount + paidChaukaAmount;
        Double totalCutPieceAmount = totalChaukaCutPieceAmount + totalLungiCutPieceAmount;

        Long totalLungiCone = new Long(0);
        Double totalLungiConeWeight = 0.0;
        Long totalLungiBheem = new Long(0);
        Double totalLungiBheemLength = 0.0;
        Double totalLungiBheemNetWeight = 0.0;

        Long totalChaukaCone = new Long(0);
        Double totalChaukaConeWeight = 0.0;
        Long totalChaukaBheem = new Long(0);
        Double totalChaukaBheemLength = 0.0;
        Double totalChaukaBheemNetWeight = 0.0;

        for(ConeBheem coneBheem: coneBheemList) {
            if (coneBheem.getProduct().equals("LUNGI")) {
                if (coneBheem.getType().equals("CONE")) {
                    totalLungiCone += coneBheem.getQuantity();
                    totalLungiConeWeight += coneBheem.getWeight();
                } else {
                    totalLungiBheem ++;
                    totalLungiBheemLength += coneBheem.getLength();
                    totalLungiBheemNetWeight += coneBheem.getWeight() - coneBheem.getPipeWeight();
                }
            } else {
                if (coneBheem.getType().equals("CONE")) {
                    totalChaukaCone += coneBheem.getQuantity();
                    totalChaukaConeWeight += coneBheem.getWeight();
                } else {
                    totalChaukaBheem ++;
                    totalChaukaBheemLength += coneBheem.getLength();
                    totalChaukaBheemNetWeight += coneBheem.getWeight() - coneBheem.getPipeWeight();
                }
            }
        }
        Double totalLungiBheemAmount = totalLungiBheem * 200.0;
        Double pendingLungiAmount = totalLungiAmount - paidLungiAmount - totalLungiCutPieceAmount - totalLungiBheemAmount;
        Double totalPendingAmount = pendingLungiAmount + pendingChaukaAmount;

        OverallDetailsResponse response = new OverallDetailsResponse();

        response.setName(name);

        response.setTotalThan(totalThan);
        response.setExtraLungi(extraLungi);
        response.setTotalLungi(totalLungi);
        response.setTotalLungiBheem(totalLungiBheem);
        response.setTotalLungiBheemAmount(totalLungiBheemAmount);
        response.setTotalLungiCone(totalLungiCone);
        response.setTotalLungiConeWeight(totalLungiConeWeight);
        response.setTotalLungiBheemLength(totalLungiBheemLength);
        response.setTotalLungiBheemNetWeight(totalLungiBheemNetWeight);
        response.setTotalLungiCutPiece(totalLungiCutPiece);
        response.setTotalLungiCutPieceAmount(totalLungiCutPieceAmount);
        response.setTotalLungiAmount(totalLungiAmount);
        response.setPaidLungiAmount(paidLungiAmount);
        response.setPendingLungiAmount(pendingLungiAmount);

        response.setTotalChauka(totalChauka);
        response.setTotalChaukaCone(totalChaukaCone);
        response.setTotalChaukaConeWeight(totalChaukaConeWeight);
        response.setTotalChaukaBheem(totalChaukaBheem);
        response.setTotalChaukaBheemLength(totalChaukaBheemLength);
        response.setTotalChaukaBheemNetWeight(totalChaukaBheemNetWeight);
        response.setTotalChaukaCutPiece(totalChaukaCutPiece);
        response.setTotalChaukaCutPieceAmount(totalChaukaCutPieceAmount);
        response.setTotalChaukaAmount(totalChaukaAmount);
        response.setPaidChaukaAmount(paidChaukaAmount);
        response.setPendingChaukaAmount(pendingChaukaAmount);

        response.setTotalAmount(totalAmount);
        response.setTotalPaidAmount(totalPaidAmount);
        response.setTotalCutPieceAmount(totalCutPieceAmount);
        response.setTotalPendingAmount(totalPendingAmount);
        response.setTotalBheemAmount(totalLungiBheemAmount);

        response.setStartDate(request.getStartDate());
        response.setEndDate(request.getEndDate());

        return response;
    }
}
