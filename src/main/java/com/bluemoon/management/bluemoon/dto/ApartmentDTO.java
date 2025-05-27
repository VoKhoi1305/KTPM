package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentDTO { // For general responses
    private Integer apartmentId;
    private Integer apartmentTypeId; // Send ID, or nested ApartmentTypeDTO
    private double usableAreaSqm;
    private Integer currentHeadResidentId;
    private ApartmentUsageStatus usageStatus;
    private LocalDate handoverDate;
    private Integer apartmentNumber;
}