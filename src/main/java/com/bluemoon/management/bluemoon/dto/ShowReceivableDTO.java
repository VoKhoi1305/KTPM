package com.bluemoon.management.bluemoon.dto;


import com.bluemoon.management.bluemoon.enums.ReceivableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowReceivableDTO {
    private Integer receivableId;
    private Integer apartmentName;
    private String feeName;
    private Integer quantity;
    private ReceivableStatus status;
    private double price;
    private LocalDate receivableIssueDate;
}
