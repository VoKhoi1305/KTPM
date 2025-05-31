package com.bluemoon.management.bluemoon.repository;


import com.bluemoon.management.bluemoon.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("SELECT v FROM Vehicle v LEFT JOIN FETCH v.apartment LEFT JOIN FETCH v.ownerResident")
    List<Vehicle> findAllWithAssociations();

    @Modifying
    @Query(value = "INSERT INTO vehicles (apartment_id, owner_resident_id, vehicle_type, " +
            "license_plate, parking_card_id, assigned_parking_spot, " +
            "brand, model, registration_date, deregistration_date, vehicles_status) " +
            "SELECT a.apartment_id, r.resident_id, CAST(:vehicleType AS vehicle_type), " +
            ":licensePlate, :parkingCardId, :assignedParkingSpot, " +
            ":brand, :model, :registrationDate, :deregistrationDate, CAST(:vehiclesStatus AS vehicle_status) " +
            "FROM apartments a CROSS JOIN residents r " +
            "WHERE a.apartment_number = :apartmentNumber " +
            "AND r.id_card_number = :cardIdNumber", nativeQuery = true)
    void insertVehicleByApartmentAndResident(@Param("apartmentNumber") Integer apartmentNumber,
                                             @Param("cardIdNumber") String cardIdNumber,
                                             @Param("vehicleType") String vehicleType,
                                             @Param("licensePlate") String licensePlate,
                                             @Param("parkingCardId") String parkingCardId,
                                             @Param("assignedParkingSpot") String assignedParkingSpot,
                                             @Param("brand") String brand,
                                             @Param("model") String model,
                                             @Param("registrationDate") LocalDate registrationDate,
                                             @Param("deregistrationDate") LocalDate deregistrationDate,
                                             @Param("vehiclesStatus") String vehiclesStatus);

    @Query(value = "SELECT v.vehicle_id FROM vehicles v " +
            "JOIN apartments a ON v.apartment_id = a.apartment_id " +
            "JOIN residents r ON v.owner_resident_id = r.resident_id " +
            "WHERE a.apartment_number = :apartmentNumber " +
            "AND r.id_card_number = :cardIdNumber " +
            "AND v.license_plate = :licensePlate " +
            "ORDER BY v.vehicle_id DESC LIMIT 1", nativeQuery = true)
    Integer findVehicleIdByDetails(@Param("apartmentNumber") Integer apartmentNumber,
                                   @Param("cardIdNumber") String cardIdNumber,
                                   @Param("licensePlate") String licensePlate);

    Vehicle findByLicensePlate(String licensePlate);
}
