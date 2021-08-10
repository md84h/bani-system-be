package com.example.lungi.service;

import com.example.lungi.exception.ConeBheemNotFoundException;
import com.example.lungi.model.Than;
import com.example.lungi.payload.request.MultiplePaymentRequest;
import com.example.lungi.payload.request.PaymentDetails;
import com.example.lungi.payload.request.PaymentRequest;
import com.example.lungi.payload.response.AuthMessage;
import com.example.lungi.repository.ThanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Date;
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

    public AuthMessage payMultipleAmount(MultiplePaymentRequest multiplePaymentRequest) {
        List<PaymentDetails> paymentRequestList = multiplePaymentRequest.getList();
        Date date = multiplePaymentRequest.getPaymentDate();
        String comment = multiplePaymentRequest.getComment();
        for (PaymentDetails details: paymentRequestList) {
            Long id = details.getId();
            Optional<Than> thanOptional = repository.findById(id);
            if(thanOptional.isPresent()) {
                Than than = repository.getById(id);
                than.setComputedAmount(details.getComputedAmount());
                than.setTotalAmount(details.getTotalAmount());
                than.setPaymentDate(date);
                than.setStatus("DONE");
                than.setComment(comment);
                repository.save(than);
            } else {
                throw new ConeBheemNotFoundException();
            }
        }
        AuthMessage message = new AuthMessage("Payment added Successfully");
        return message;
    }

    public AuthMessage deleteById(Long id) {
        Optional<Than> than = repository.findById(id);
        if (than.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ConeBheemNotFoundException();
        }
        AuthMessage message = new AuthMessage("Deleted Successfully");
        return message;
    }
}
