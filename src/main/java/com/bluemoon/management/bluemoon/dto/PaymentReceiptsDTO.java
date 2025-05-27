package com.bluemoon.management.bluemoon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReceiptsDTO {
    private Integer receiptId;
    private Integer apartmentId;
    private double totalAmountReceived;
    private Timestamp receiptTimestamp;
    private String notes;
    private Integer receivableId;
    
}
