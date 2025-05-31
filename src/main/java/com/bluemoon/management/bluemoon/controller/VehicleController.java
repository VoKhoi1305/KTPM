package com.bluemoon.management.bluemoon.controller;

import com.bluemoon.management.bluemoon.dto.VehicleCreateRequest;
import com.bluemoon.management.bluemoon.dto.VehicleDTO;
import com.bluemoon.management.bluemoon.dto.VehicleResponse;
import com.bluemoon.management.bluemoon.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<VehicleDTO>> getAllReceivables() {
        List<VehicleDTO> vehicleDTOS = vehicleService.getVehicles();
        return ResponseEntity.status(HttpStatus.OK).body(vehicleDTOS);
    }

    @GetMapping("/get/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Integer vehicleId) {
        VehicleDTO vehicle = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleResponse> createVehicleByQuery(@RequestBody VehicleCreateRequest request) {
            VehicleResponse response = vehicleService.createVehicleByQuery(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
