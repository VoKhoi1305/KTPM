package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.Gender;
import com.bluemoon.management.bluemoon.enums.ResidencyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentsDTO {
    private int residentId;
    private String residentName;
    private String residentPhoneNumber;
    private LocalDate residentDoB;
    private Gender gender;
    private int permanentResidenceApartmentId;
    private String relationshipToHead;
    private String nationality;
    private ResidencyStatus residencyStatus;
    private String placeOfBirth;
    private String IDcardNumber;
    private LocalDate IDCardIssueDate;
}
