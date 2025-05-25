package com.bluemoon.management.bluemoon.controller;


import com.bluemoon.management.bluemoon.dto.ResidentsDTO;
import com.bluemoon.management.bluemoon.entity.Resident;
import com.bluemoon.management.bluemoon.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
