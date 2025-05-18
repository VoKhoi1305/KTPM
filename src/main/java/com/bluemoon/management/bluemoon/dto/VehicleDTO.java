package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.VehicleStatus;
import com.bluemoon.management.bluemoon.enums.VehicleType;

import java.time.LocalDate;
import java.time.LocalTime;

public class VehicleDTO {
    private int vehicleId;
    private int apartmentId;
    private int ownerId;
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
