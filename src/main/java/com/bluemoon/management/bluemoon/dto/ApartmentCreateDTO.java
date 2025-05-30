package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentCreateDTO {
    private Integer apartmentTypeId;// Send ID, or nested ApartmentTypeDTO
    private double usableAreaSqm;
    private Integer apartmentNumber;
    private ApartmentUsageStatus usageStatus;
    private LocalDate handoverDate;
}