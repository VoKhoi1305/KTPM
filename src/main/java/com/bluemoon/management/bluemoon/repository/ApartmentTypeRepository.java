package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.ApartmentType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface ApartmentTypeRepository extends JpaRepository<ApartmentType, Integer> {

}
