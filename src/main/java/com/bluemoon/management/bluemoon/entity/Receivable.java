package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "receivables")
public class Receivable {
    @Id
    @Column(name = "receivable_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "fee_type_id", nullable = false)
    private FeeType feeType;

    @Column(name = "quantity", precision = 12, scale = 2)
    private BigDecimal quantity;

    @ColumnDefault("0.00")
    @Column(name = "base_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal baseAmount;

    @ColumnDefault("0.00")
    @Column(name = "adjustment_amount", precision = 15, scale = 2)
    private BigDecimal adjustmentAmount;

    @ColumnDefault("(base_amount + adjustment_amount)")
    @Column(name = "amount_due", precision = 15, scale = 2)
    private BigDecimal amountDue;

    @ColumnDefault("0.00")
    @Column(name = "amount_paid", nullable = false, precision = 15, scale = 2)
    private BigDecimal amountPaid;

    @ColumnDefault("((base_amount + adjustment_amount) - amount_paid)")
    @Column(name = "balance", precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "receivables_issue_date")
    private LocalDate receivablesIssueDate;

    @Column(name = "payment_deadline")
    private LocalDate paymentDeadline;

    @Column(name = "billing_period", length = 20)
    private String billingPeriod;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'receivables_status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'Unissued'")
    @Column(name = "receivables_status", columnDefinition = "receivable_status not null")
    private Object receivablesStatus;
*/
}