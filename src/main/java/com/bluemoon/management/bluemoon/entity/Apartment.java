package com.bluemoon.management.bluemoon.entity;

import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @Column(name = "apartment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "apartment_type_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    @Column(name = "handover_date")
    private LocalDate handoverDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "current_head_resident_id")
    private Resident currentHeadResident;


    @Column(name = "usable_area_sqm")
    private Double usableAreaSqm;


    @ColumnDefault("'Vacant'")
    @Column(name = "usage_status", columnDefinition = "apartment_usage_status not null")
    @Enumerated(EnumType.STRING)
    private ApartmentUsageStatus usageStatus;
}