package com.bluemoon.management.bluemoon.dto;

import com.bluemoon.management.bluemoon.enums.CalculationMethod;
import com.bluemoon.management.bluemoon.enums.FeeCategory;
import com.bluemoon.management.bluemoon.enums.FeeFrequency;
import com.bluemoon.management.bluemoon.enums.FeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeTypesDTO {
    private int typeId;
    private String feeName;
    private FeeCategory category;
    private LocalDate feeIssueDate;
    private FeeFrequency feeFrequency;
    private CalculationMethod calculationMethod;
    private LocalDate dueDate;
    private FeeStatus feeStatus;
    private double unitPrice;
    private boolean isMandatory;
    private boolean applyToAllActive;


}
