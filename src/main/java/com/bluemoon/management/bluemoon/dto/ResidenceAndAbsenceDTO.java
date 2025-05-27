package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.RAType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidenceAndAbsenceDTO {
    private Integer raID;
    private Integer residentID;
    private RAType raType;
    private LocalDate raFrom;
    private LocalDate raTo;
    private String raReason;
    private String raAddress;
    private Timestamp raRegisterTime;
}
