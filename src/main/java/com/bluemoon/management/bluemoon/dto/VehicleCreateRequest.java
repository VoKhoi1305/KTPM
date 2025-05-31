package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.VehicleStatus;
import com.bluemoon.management.bluemoon.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreateRequest {
    private Integer apartmentNumber;
    private String cardIdNumber;
    private VehicleType vehicleType;
    private String licensePlate;
    private String parkingCardId;
    private String assignedParkingSpot;
    private String brand;
    private String model;
    private LocalDate registrationDate;
    private LocalDate deregistrationDate;
    private VehicleStatus vehiclesStatus;
}
