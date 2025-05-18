package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
//    @Query("SELECT a FROM Apartment a LEFT JOIN FETCH a.apartmentType LEFT JOIN FETCH a.currentHeadResident")
//    List<Apartment> findAllWithDetails();
}
