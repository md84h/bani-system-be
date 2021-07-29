package com.example.lungi.service;

import com.example.lungi.exception.ConeBheemNotFoundException;
import com.example.lungi.model.ConeBheem;
import com.example.lungi.payload.response.ConeBheemResponse;
import com.example.lungi.repository.ConeBheemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConeBheemService {
    @Autowired
    ConeBheemRepository repository;

    public List<ConeBheemResponse> getDetailsByEmployeeId(Long id) {
        List<com.example.lungi.model.ConeBheem> coneBheems = repository.findByEmployeeId(id);
        List<ConeBheemResponse> coneBheemResponseList = new ArrayList<ConeBheemResponse>();
        for (com.example.lungi.model.ConeBheem coneBheem: coneBheems) {
            ConeBheemResponse coneBheemResponse = new ConeBheemResponse();
            coneBheemResponse.setDate(coneBheem.getDate());
            coneBheemResponse.setEmployeeId(coneBheem.getEmployeeId());
            coneBheemResponse.setId(coneBheem.getId());
            coneBheemResponse.setLength(coneBheem.getLength());
            coneBheemResponse.setPipeWeight(coneBheem.getPipeWeight());
            coneBheemResponse.setProduct(coneBheem.getProduct());
            coneBheemResponse.setQuantity(coneBheem.getQuantity());
            coneBheemResponse.setStatus(coneBheem.getStatus());
            coneBheemResponse.setType(coneBheem.getType());
            coneBheemResponse.setWeight(coneBheem.getWeight());
            coneBheemResponse.setNetWeight(coneBheem.getType().equals("BHEEM") ? coneBheem.getWeight() - coneBheem.getPipeWeight() : null);
            coneBheemResponseList.add(coneBheemResponse);
        }
        return coneBheemResponseList;
    }

    public List<ConeBheemResponse> getDetailsByEmployeeIdByProductByType(Long id, String product, String type) {
        List<ConeBheem> coneBheems = repository.findByEmployeeIdByProductByType(id, product, type);
        List<ConeBheemResponse> coneBheemResponseList = new ArrayList<ConeBheemResponse>();
        for (com.example.lungi.model.ConeBheem coneBheem: coneBheems) {
            ConeBheemResponse coneBheemResponse = new ConeBheemResponse();
            coneBheemResponse.setDate(coneBheem.getDate());
            coneBheemResponse.setEmployeeId(coneBheem.getEmployeeId());
            coneBheemResponse.setId(coneBheem.getId());
            coneBheemResponse.setLength(coneBheem.getLength());
            coneBheemResponse.setPipeWeight(coneBheem.getPipeWeight());
            coneBheemResponse.setProduct(coneBheem.getProduct());
            coneBheemResponse.setQuantity(coneBheem.getQuantity());
            coneBheemResponse.setStatus(coneBheem.getStatus());
            coneBheemResponse.setType(coneBheem.getType());
            coneBheemResponse.setWeight(coneBheem.getWeight());
            coneBheemResponse.setNetWeight(coneBheem.getType().equals("BHEEM") ? coneBheem.getWeight() - coneBheem.getPipeWeight() : null);
            coneBheemResponseList.add(coneBheemResponse);
        }
        return coneBheemResponseList;
    }

    public com.example.lungi.model.ConeBheem createDetails(com.example.lungi.model.ConeBheem coneBheem) {
        return repository.save(coneBheem);
    }

    public com.example.lungi.model.ConeBheem updateDetails(com.example.lungi.model.ConeBheem coneBheem, Long id) {
        Optional<com.example.lungi.model.ConeBheem> coneBheemOptional = repository.findById(id);
        if (coneBheemOptional.isPresent()) {
            return repository.save(coneBheem);
        } else {
            throw new ConeBheemNotFoundException();
        }
    }

    public com.example.lungi.model.ConeBheem updateStatus(Long id, String status) {
        com.example.lungi.model.ConeBheem coneBheem = repository.findById(id).get();
        coneBheem.setStatus(status);
        return repository.save(coneBheem);
    }
}
