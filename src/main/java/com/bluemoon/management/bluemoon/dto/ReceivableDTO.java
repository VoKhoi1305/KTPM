package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.ReceivableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivableDTO {
    private int receivableId;
    private int apartmentId;
    private int feeTypeId;
    private ReceivableStatus receivableStatus;
    private int quantity;
    private double baseAmount;
    private LocalDate receivableIssueDate;
    private LocalDate receivableDeadline;

}
