package com.bluemoon.management.bluemoon.entity;

import com.bluemoon.management.bluemoon.enums.CalculationMethod;
import com.bluemoon.management.bluemoon.enums.FeeCategory;
import com.bluemoon.management.bluemoon.enums.FeeFrequency;
import com.bluemoon.management.bluemoon.enums.FeeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fee_types")
public class FeeType {
    @Id
    @Column(name = "fee_type_id", nullable = false)
    private Integer id;

    @Column(name = "fee_name", nullable = false)
    private String feeName;

    @Column(name = "unit_price",columnDefinition = "numeric")
    private double unitPrice;

    @ColumnDefault("true")
    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory = false;

    @Column(name = "category", columnDefinition = "fee_category not null")
    @Enumerated(EnumType.STRING)
    private FeeCategory category;


    @Column(name = "frequency", columnDefinition = "fee_frequency not null")
    @Enumerated(EnumType.STRING)
    private FeeFrequency frequency;

    @Column(name = "calculation_method", columnDefinition = "fee_calculation_method not null")
    @Enumerated(EnumType.STRING)
    private CalculationMethod calculationMethod;

    @ColumnDefault("'Draft'")
    @Column(name = "fee_status", columnDefinition = "fee_status not null")
    @Enumerated(EnumType.STRING)
    private FeeStatus feeStatus;

    @ColumnDefault("FALSE")
    @Column(name ="is_apply_to_all")
    private Boolean isApplyToAll;

}