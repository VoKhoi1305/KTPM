package com.bluemoon.management.bluemoon.entity;

import com.bluemoon.management.bluemoon.enums.ReceivableStatus;
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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "fee_type_id", nullable = false)
    private FeeType feeType;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "receivables_issue_date")
    private LocalDate receivablesIssueDate;

    @Column(name = "payment_deadline")
    private LocalDate paymentDeadline;


    @ColumnDefault("'Unissued'")
    @Column(name = "receivables_status", columnDefinition = "receivable_status not null")
    @Enumerated(EnumType.STRING)
    private ReceivableStatus receivablesStatus;
}