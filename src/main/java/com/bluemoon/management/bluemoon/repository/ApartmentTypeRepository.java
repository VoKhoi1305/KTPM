package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.ApartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentTypeRepository extends JpaRepository<ApartmentType, Integer> {

    Optional<ApartmentType> findById(Integer id);
}
