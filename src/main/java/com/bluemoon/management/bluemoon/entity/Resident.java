package com.bluemoon.management.bluemoon.entity;

import com.bluemoon.management.bluemoon.enums.Gender;
import com.bluemoon.management.bluemoon.enums.ResidencyStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

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

    @Column(name = "resident_gender", columnDefinition = "gender not null")
    @Enumerated(EnumType.STRING)
    private Gender residentGender;

    @ColumnDefault("'Undefined'")
    @Column(name = "residency_status", columnDefinition = "residency_status not null")
    @Enumerated(EnumType.STRING)
    private ResidencyStatus residencyStatus;

}