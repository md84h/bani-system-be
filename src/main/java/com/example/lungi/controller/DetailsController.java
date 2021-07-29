package com.example.lungi.controller;

import com.example.lungi.payload.response.ConeBheemResponse;
import com.example.lungi.service.ConeBheemService;
import com.example.lungi.service.ThanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DetailsController {
    @Autowired
    ConeBheemService coneBheemService;

    @Autowired
    ThanService thanService;

    @GetMapping("/details/{id}/{product}/{type}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> getDetailsByEmployeeIdProductType(@PathVariable Long id, @PathVariable String product, @PathVariable String type) {
        if (type.equals("THAN") || (type.equals("CHAUKA") && product.equals("CHAUKA"))) {
            return ResponseEntity.ok().body(thanService.getDetailsByEmployeeIdByProduct(id, product));
        } else {
            return ResponseEntity.ok().body(coneBheemService.getDetailsByEmployeeIdByProductByType(id, product, type));
        }
    }
}
