package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.RAType;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ResidenceAndAbsenceDTO {
    private int raID;
    private int residentID;
    private RAType raType;
    private LocalDate raFrom;
    private LocalDate raTo;
    private String raReason;
    private String raAddress;
    private Timestamp raRegisterTime;
}
