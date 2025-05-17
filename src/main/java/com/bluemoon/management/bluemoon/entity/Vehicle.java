package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(name = "vehicle_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "owner_resident_id")
    private Resident ownerResident;

    @Column(name = "license_plate", length = 15)
    private String licensePlate;

    @Column(name = "parking_card_id", length = 50)
    private String parkingCardId;

    @Column(name = "assigned_parking_spot", length = 50)
    private String assignedParkingSpot;

    @Column(name = "brand", length = 50)
    private String brand;

    @Column(name = "model", length = 100)
    private String model;

    @Column(name = "vin_number", length = 50)
    private String vinNumber;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "deregistration_date")
    private LocalDate deregistrationDate;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'vehicle_type' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "vehicle_type", columnDefinition = "vehicle_type not null")
    private Object vehicleType;
*/
/*
 TODO [Reverse Engineering] create field to map the 'vehicles_status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'PendingApproval'")
    @Column(name = "vehicles_status", columnDefinition = "vehicle_status not null")
    private Object vehiclesStatus;
*/
}