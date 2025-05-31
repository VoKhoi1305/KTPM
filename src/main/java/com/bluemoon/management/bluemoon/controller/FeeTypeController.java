package com.bluemoon.management.bluemoon.controller;

import com.bluemoon.management.bluemoon.dto.FeeTypesDTO;
import com.bluemoon.management.bluemoon.entity.FeeType;
import com.bluemoon.management.bluemoon.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/feetype")
public class FeeTypeController {
    private final FeeTypeService feeTypeService;

    @Autowired
    public FeeTypeController(FeeTypeService feeTypeService) {
        this.feeTypeService = feeTypeService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<FeeTypesDTO>> getAll() {
        List<FeeTypesDTO> feeTypes = feeTypeService.getAllFeeTypes();
        return ResponseEntity.status(HttpStatus.OK).body(feeTypes);
    }

    @PostMapping("/add")
    public ResponseEntity<FeeTypesDTO> addFeeType(@RequestBody FeeTypesDTO feeTypesDTO) {
        FeeTypesDTO createdFeeType = feeTypeService.insertFeeType(feeTypesDTO);
        return new ResponseEntity<>(createdFeeType, HttpStatus.CREATED);
    }

}
