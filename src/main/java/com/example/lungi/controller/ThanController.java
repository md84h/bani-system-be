package com.example.lungi.controller;

import com.example.lungi.model.Than;
import com.example.lungi.payload.request.PaymentRequest;
import com.example.lungi.service.ThanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/than")
public class ThanController {
    @Autowired
    ThanService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Than>> getDetailsByEmployeeId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getDetailsByEmployeeId(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Than> createDetails(@RequestBody Than than) {
        return ResponseEntity.ok().body(service.createDetails(than));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Than> updateDetails(@RequestBody Than than, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateDetails(id, than));
    }

    @PostMapping("/{id}/pay")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Than> payAmount(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok().body(service.payAmount(id, paymentRequest));
    }

}
