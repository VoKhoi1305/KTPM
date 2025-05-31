package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    @Query(value = "INSERT INTO apartments (apartment_type_id, apartment_number, usable_area_sqm, usage_status, handover_date) " +
            "VALUES (:typeId, :apartmentNumber , :area, CAST(:status AS apartment_usage_status), :handoverDate) " +
            "RETURNING apartment_id", nativeQuery = true)
    Integer insertApartmentWithCast(
            @Param("typeId") Integer apartmentTypeId,
            @Param("apartmentNumber") Integer apartmentNumber,
            @Param("area") Double usableAreaSqm,
            @Param("status") String usageStatus,
            @Param("handoverDate") LocalDate handoverDate);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE apartments SET current_head_resident_id = :residentId WHERE apartment_id = :apartmentId",
            nativeQuery = true
    )
    void updateHeadResident(
            @Param("apartmentId") Integer apartmentId,
            @Param("residentId") Integer residentId
    );

    @Modifying
    @Query(
            value = "UPDATE apartments SET usage_status = CAST(:status AS apartment_usage_status) WHERE apartment_id = :apartmentId ",
            nativeQuery = true)
    void updateUsageStatus(
            @Param("apartmentId") Integer apartmentId,
            @Param("status") String usageStatus
    );

    Optional<Apartment> findByApartmentNumber(Integer apartmentNumber);
}
