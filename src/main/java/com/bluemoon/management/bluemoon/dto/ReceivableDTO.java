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
    private Integer receivableId;
    private Integer apartmentId;
    private Integer feeTypeId;
    private ReceivableStatus receivableStatus;
    private Integer quantity;
    private LocalDate receivableDeadline;

}
