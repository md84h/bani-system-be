package com.example.lungi.controller;

import com.example.lungi.payload.response.ConeBheemResponse;
import com.example.lungi.service.ConeBheemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ConeBheemController {
    @Autowired
    ConeBheemService service;

    @GetMapping("/cone-bheem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ConeBheemResponse>> getDetailsByEmployeeId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getDetailsByEmployeeId(id));
    }

    @PostMapping("/cone-bheem")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<com.example.lungi.model.ConeBheem> createDetails(@RequestBody com.example.lungi.model.ConeBheem coneBheem) {
        return ResponseEntity.ok().body(service.createDetails(coneBheem));
    }

    @PutMapping("/cone-bheem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<com.example.lungi.model.ConeBheem> updateDetails(@RequestBody com.example.lungi.model.ConeBheem coneBheem, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateDetails(coneBheem, id));
    }

    @PutMapping("/cone-bheem/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<com.example.lungi.model.ConeBheem> updateStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok().body(service.updateStatus(id, status));
    }
}
