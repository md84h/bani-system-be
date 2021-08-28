package com.example.lungi.controller;

import com.example.lungi.model.CreditDebit;
import com.example.lungi.payload.response.AuthMessage;
import com.example.lungi.service.CreditDebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/credit-debit")
public class CreditDebitController {
    @Autowired
    CreditDebitService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CreditDebit>> getDetailsByEmployeeId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getDetailsByEmployeeId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CreditDebit> addCreditDebitEntry(@RequestBody CreditDebit creditDebit) {
        return ResponseEntity.ok().body(service.addCreditDebitEntry(creditDebit));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CreditDebit> updateCreditDebitEntry(@RequestBody CreditDebit creditDebit, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateCreditDebitEntry(creditDebit, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthMessage> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.deleteById(id));
    }
}
