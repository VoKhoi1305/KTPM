package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.*;
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

    @Column(name = "apartment_code", nullable = false, length = 20)
    private String apartmentCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "apartment_type_id", nullable = false)
    private ApartmentType apartmentType;

    @Column(name = "handover_date")
    private LocalDate handoverDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "current_head_resident_id")
    private Resident currentHeadResident;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "owner_user_id")
    private User ownerUser;

    @Column(name = "owner_name_legal")
    private String ownerNameLegal;

    @Column(name = "owner_phone_legal", length = 15)
    private String ownerPhoneLegal;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'usage_status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'Vacant'")
    @Column(name = "usage_status", columnDefinition = "apartment_usage_status not null")
    private Object usageStatus;
*/
}