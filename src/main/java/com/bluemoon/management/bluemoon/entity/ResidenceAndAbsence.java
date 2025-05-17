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
@Table(name = "residence_and_absence")
public class ResidenceAndAbsence {
    @Id
    @Column(name = "ra_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;

    @Column(name = "ra_from_time", nullable = false)
    private LocalDate raFromTime;

    @Column(name = "ra_to_time", nullable = false)
    private LocalDate raToTime;

    @Column(name = "ra_reason", length = Integer.MAX_VALUE)
    private String raReason;

    @Column(name = "ra_address")
    private String raAddress;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ra_register_time")
    private OffsetDateTime raRegisterTime;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'ra_type' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "ra_type", columnDefinition = "residence_absence_type not null")
    private Object raType;
*/
}