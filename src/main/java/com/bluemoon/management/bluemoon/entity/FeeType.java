package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fee_types")
public class FeeType {
    @Id
    @Column(name = "fee_type_id", nullable = false)
    private Integer id;

    @Column(name = "fee_code", nullable = false, length = 50)
    private String feeCode;

    @Column(name = "fee_name", nullable = false)
    private String feeName;

    @Column(name = "fee_issue_date", nullable = false)
    private LocalDate feeIssueDate;

    @Column(name = "due_date")
    private LocalDate dueDate;
    @ColumnDefault("true")
    @Column(name = "apply_to_all_active")
    private Boolean applyToAllActive;

    @Column(name = "applicable_apartment_type_ids")
    private List<Integer> applicableApartmentTypeIds;

    @Column(name = "unit_price", precision = 15, scale = 2)
    private BigDecimal unitPrice;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ColumnDefault("true")
    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory = false;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'category' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "category", columnDefinition = "fee_category not null")
    private Object category;
*/
/*
 TODO [Reverse Engineering] create field to map the 'frequency' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "frequency", columnDefinition = "fee_frequency not null")
    private Object frequency;
*/
/*
 TODO [Reverse Engineering] create field to map the 'calculation_method' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "calculation_method", columnDefinition = "fee_calculation_method not null")
    private Object calculationMethod;
*/
/*
 TODO [Reverse Engineering] create field to map the 'fee_status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'Draft'")
    @Column(name = "fee_status", columnDefinition = "fee_status not null")
    private Object feeStatus;
*/
}