package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ResidentsRepository extends JpaRepository<Resident, Integer> {
}
