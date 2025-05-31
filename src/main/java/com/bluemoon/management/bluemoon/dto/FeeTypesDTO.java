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
    private FeeFrequency feeFrequency;
    private CalculationMethod calculationMethod;
    private FeeStatus feeStatus;
    private double unitPrice;
    private Boolean isMandatory;  // Đổi thành Boolean
    private Boolean isApplyToAll; // Đổi thành Boolean
}
