package com.example.lungi.service;

import com.example.lungi.exception.ConeBheemNotFoundException;
import com.example.lungi.model.Than;
import com.example.lungi.payload.request.PaymentRequest;
import com.example.lungi.repository.ThanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ThanService {
    @Autowired
    ThanRepository repository;

    public List<Than> getDetailsByEmployeeId(Long id) {
        return repository.findByEmployeeId(id);
    }

    public List<Than> getDetailsByEmployeeIdByProduct(Long id, String product) {
        return repository.findByEmployeeIdByProduct(id, product);
    }

    public Than createDetails(Than than) {
        return repository.save(than);
    }

    public Than updateDetails(Long id, Than than) {
        Optional<Than> thanOptional = repository.findById(id);
        if (thanOptional.isPresent()) {
            return repository.save(than);
        } else {
            throw new ConeBheemNotFoundException();
        }
    }

    public Than payAmount(Long id, PaymentRequest paymentRequest) {
        Optional<Than> thanOptional = repository.findById(id);
        if (thanOptional.isPresent()) {
            Than than = repository.getById(id);
            than.setComputedAmount(paymentRequest.getComputedAmount());
            than.setTotalAmount(paymentRequest.getTotalAmount());
            than.setPaymentDate(paymentRequest.getPaymentDate());
            than.setStatus(paymentRequest.getStatus());
            than.setComment(paymentRequest.getComment());

            return repository.save(than);
        } else {
            throw new ConeBheemNotFoundException();
        }
    }
}
