package com.example.lungi.controller;

import com.example.lungi.payload.request.OverallDetailsRequest;
import com.example.lungi.payload.response.OverallDetailsResponse;
import com.example.lungi.service.OverallDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/overall-details")
public class OverallDetailsController {
    @Autowired
    OverallDetailsService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OverallDetailsResponse> getOverallDetails(@RequestBody OverallDetailsRequest request) {
        return ResponseEntity.ok().body(service.getOverallDetails(request));
    }
}
