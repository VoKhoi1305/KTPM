package com.bluemoon.management.bluemoon.controller;

import com.bluemoon.management.bluemoon.dto.ApartmentCreateDTO;
import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;
import com.bluemoon.management.bluemoon.repository.ApartmentRepository;
import com.bluemoon.management.bluemoon.service.ApartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;
    @Autowired
    public ApartmentController(ApartmentService apartmentService){
        this.apartmentService = apartmentService;

    }

    @GetMapping("/get")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ApartmentDTO>> getAll() {
        List<ApartmentDTO> apartments = apartmentService.getAllApartments();
        return ResponseEntity.status(HttpStatus.OK).body(apartments);
    }


    @GetMapping("/get/{apartmentId}")
    @Transactional(readOnly = true)
    public ResponseEntity<ApartmentDTO> getApartmentById(@PathVariable Integer apartmentId) {
        ApartmentDTO apartment = apartmentService.getApartmentById(apartmentId);
        return ResponseEntity.status(HttpStatus.OK).body(apartment);
    }

    /**
     * API tạo apartment mới
     */
    @PostMapping("/add")
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentCreateDTO apartmentCreateDTO) {
        ApartmentDTO createdApartment = apartmentService.createApartment(apartmentCreateDTO);
        return new ResponseEntity<>(createdApartment, HttpStatus.CREATED);
    }



    @PutMapping("/updateheadresident")
    public ResponseEntity<ApartmentDTO> updateHeadResidentId(@RequestBody Map<String, Integer> apartmentIdMap) {
        Integer updateApartmentId = apartmentIdMap.get("updateApartmentId");
        Integer updateHeadResidentId = apartmentIdMap.get("updateHeadResidentId");
        ApartmentDTO updateApartment = apartmentService.updateHeadResidentId(updateApartmentId, updateHeadResidentId);
        return new ResponseEntity<>(updateApartment, HttpStatus.OK);
    }

    @PutMapping("/updateusagestatus")
    public ResponseEntity<ApartmentDTO> updateUsageStatus(
            @RequestBody Map<String, Object> requestBody) {
        Integer updateApartmentId = (Integer) requestBody.get("updateApartmentId");
        String updateUsageStatus = requestBody.get("updateUsageStatus").toString();
        ApartmentDTO updateApartment = apartmentService.updateApartmentUsageStatus(updateApartmentId, ApartmentUsageStatus.valueOf(updateUsageStatus));
        return new ResponseEntity<>(updateApartment, HttpStatus.OK);
    }
}
