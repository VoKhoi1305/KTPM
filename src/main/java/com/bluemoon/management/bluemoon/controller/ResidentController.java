package com.bluemoon.management.bluemoon.controller;


import com.bluemoon.management.bluemoon.dto.ApartmentCreateDTO;
import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.dto.ResidentsDTO;
import com.bluemoon.management.bluemoon.entity.Resident;
import com.bluemoon.management.bluemoon.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/resident")
public class ResidentController {
    private final ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResidentsDTO>> get() {
        List<ResidentsDTO> residents = residentService.getAllResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResidentsDTO> createResident(@RequestBody ResidentsDTO residentsDTO) {
        ResidentsDTO createdResident = residentService.createResident(residentsDTO);
        return new ResponseEntity<>(createdResident, HttpStatus.CREATED);
    }
}
