package com.example.lungi.service;

import com.example.lungi.exception.ConeBheemNotFoundException;
import com.example.lungi.model.ConeBheem;
import com.example.lungi.model.CreditDebit;
import com.example.lungi.payload.response.AuthMessage;
import com.example.lungi.repository.CreditDebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditDebitService {
    @Autowired
    CreditDebitRepository creditDebitRepository;

    public List<CreditDebit> getDetailsByEmployeeId(Long id) {
        List<CreditDebit> creditDebits = creditDebitRepository.findByEmployeeId(id);
        return creditDebits;
    }

    public CreditDebit addCreditDebitEntry(CreditDebit creditDebit) {
        return creditDebitRepository.save(creditDebit);
    }

    public CreditDebit updateCreditDebitEntry(CreditDebit creditDebit, Long id) {
        Optional<CreditDebit> creditDebitOptional = creditDebitRepository.findById(id);
        if (creditDebitOptional.isPresent()) {
            return creditDebitRepository.save(creditDebit);
        } else {
            throw new ConeBheemNotFoundException();
        }
    }

    public AuthMessage deleteById(Long id) {
        Optional<CreditDebit> creditDebit = creditDebitRepository.findById(id);
        if (creditDebit.isPresent()) {
            creditDebitRepository.deleteById(id);
        } else {
            throw new ConeBheemNotFoundException();
        }
        AuthMessage message = new AuthMessage("Deleted Successfully");
        return message;
    }
}
