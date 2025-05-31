package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Integer> {
    @Query(value = "INSERT INTO fee_types (fee_name, category, frequency, calculation_method, fee_status, unit_price, is_mandatory, is_apply_to_all) " +
            "VALUES (:feeName, CAST(:category AS fee_category), CAST(:frequency AS fee_frequency), " +
            "CAST(:calculationMethod AS fee_calculation_method), CAST(:feeStatus AS fee_status), :unitPrice, " +
            ":isMandatory,:isApplyToAll) " +
            "RETURNING fee_type_id",  // giả sử khóa chính của bảng là `id`
            nativeQuery = true)
    Integer insertFee(
            @Param("feeName") String feeName,
            @Param("category") String category,
            @Param("frequency") String frequency,
            @Param("calculationMethod") String calculationMethod,
            @Param("feeStatus") String feeStatus,
            @Param("unitPrice") double unitPrice,
            @Param("isMandatory") boolean isMandatory,
            @Param("isApplyToAll") boolean isApplyToAll
    );


}
