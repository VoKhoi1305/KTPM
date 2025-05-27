package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.VehicleStatus;
import com.bluemoon.management.bluemoon.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Integer vehicleId;
    private Integer apartmentId;
    private Integer ownerId;
    private VehicleType vehicleType;
    private String licensePlate;
    private String model;
    private String brand;
    private String parkingCardID;
    private String assignedParkingSpot;
    private LocalDate registrationDate;
    private LocalDate deregistrationDate;
    private VehicleStatus vehicleStatus;

}
