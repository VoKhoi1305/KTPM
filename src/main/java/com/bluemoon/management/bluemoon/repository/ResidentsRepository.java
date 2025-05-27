package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface ResidentsRepository extends JpaRepository<Resident, Integer> {
        @Query(value = "INSERT INTO residents (resident_fullname, resident_phone_number, resident_dob, resident_gender, permanent_residence_apartment_id, relationship_to_head, nationality, residency_status, place_of_birth, id_card_number, id_card_issue_date)"
                + "VALUES (:fullname, :phoneNumber, :dob, CAST(:gender AS gender) , :apartment_id, :relationshipToHead, :nationality, CAST(:residencyStatus AS residency_status ), :placeOfBirth, :idCardNumber, :idCardIssueDate ) "
                +"RETURNING resident_id",nativeQuery = true)
        Integer insertResident(
                @Param("fullname") String fullName,
                @Param("phoneNumber") String phoneNumber,
                @Param("dob") LocalDate dob,
                @Param("gender") String gender,
                @Param("apartment_id") Integer apartmentId,
                @Param("relationshipToHead") String relationshipToHead,
                @Param("nationality") String nationality,
                @Param("residencyStatus") String residencyStatus,
                @Param("placeOfBirth") String placeOfBirth,
                @Param("idCardNumber") String idCardNumber,
                @Param("idCardIssueDate") LocalDate idCardIssueDate
                );
}
