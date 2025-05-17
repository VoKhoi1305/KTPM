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
@Table(name = "residents")
public class Resident {
    @Id
    @Column(name = "resident_id", nullable = false)
    private Integer id;

    @Column(name = "resident_fullname", nullable = false, length = 100)
    private String residentFullname;

    @Column(name = "resident_phone_number", length = 15)
    private String residentPhoneNumber;

    @Column(name = "resident_dob", nullable = false)
    private LocalDate residentDob;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "permanent_residence_apartment_id")
    private Apartment permanentResidenceApartment;

    @Column(name = "relationship_to_head", length = 50)
    private String relationshipToHead;

    @ColumnDefault("'Vietnamese'")
    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "id_card_number", length = 20)
    private String idCardNumber;

    @Column(name = "id_card_issue_date")
    private LocalDate idCardIssueDate;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "resident_email", length = 100)
    private String residentEmail;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'resident_gender' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "resident_gender", columnDefinition = "gender not null")
    private Object residentGender;
*/
/*
 TODO [Reverse Engineering] create field to map the 'residency_status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'Undefined'")
    @Column(name = "residency_status", columnDefinition = "residency_status not null")
    private Object residencyStatus;
*/
}