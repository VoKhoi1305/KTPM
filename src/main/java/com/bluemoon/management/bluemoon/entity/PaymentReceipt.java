package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment_receipts")
public class PaymentReceipt {
    @Id
    @Column(name = "receipt_id", nullable = false)
    private Long id;

    @Column(name = "receipt_code", nullable = false, length = 50)
    private String receiptCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @Column(name = "total_amount_received", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmountReceived;

    @Column(name = "payer_name", length = 100)
    private String payerName;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "payment_timestamp", nullable = false)
    private OffsetDateTime paymentTimestamp;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "receivable_id", nullable = false)
    private Receivable receivable;

}