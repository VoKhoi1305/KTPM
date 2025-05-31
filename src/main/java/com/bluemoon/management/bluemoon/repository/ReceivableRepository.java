package com.bluemoon.management.bluemoon.repository;

import com.bluemoon.management.bluemoon.entity.Receivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceivableRepository extends JpaRepository<Receivable, Integer> {
    @Query(value = "INSERT INTO receivables (apartment_id, fee_type_id, receivables_status, quantity, receivables_issue_date, payment_deadline) " +
            "VALUES (:apartmentId, :feeTypeId, CAST(:status AS receivable_status), :quantity, :issueDate, :deadline) " +
            "RETURNING receivable_id", nativeQuery = true)
    Integer insertReceivable(
            @Param("apartmentId") Integer apartmentId,
            @Param("feeTypeId") Integer feeTypeId,
            @Param("status") String receivablesStatus,
            @Param("quantity") Integer quantity,
            @Param("issueDate") LocalDate receivablesIssueDate,
            @Param("deadline") LocalDate paymentDeadline
    );

    @Query(value = "INSERT INTO receivables (apartment_id, fee_type_id, receivables_status, quantity, receivables_issue_date, payment_deadline) " +
            "VALUES (:apartmentId, :feeTypeId, CAST(:status AS receivable_status), 1 , :issueDate, :deadline) " +
            "RETURNING receivable_id", nativeQuery = true)
    Integer insertReceivableForAllApartments(
            @Param("apartmentId") Integer apartmentId,
            @Param("feeTypeId") Integer feeTypeId,
            @Param("status") String receivablesStatus,
            @Param("issueDate") LocalDate receivablesIssueDate,
            @Param("deadline") LocalDate paymentDeadline
    );

    @Query(value = """
        SELECT 
            r.receivable_id as receivableId,
            a.apartment_number as apartmentName,
            ft.fee_name as feeName,
            r.quantity as quantity,
            r.receivables_status as status,
            (ft.unit_price * r.quantity) as price,
            r.receivables_issue_date as receivableIssueDate
        FROM receivables r
        INNER JOIN apartments a ON r.apartment_id = a.apartment_id
        INNER JOIN fee_types ft ON r.fee_type_id = ft.fee_type_id
        ORDER BY r.receivables_issue_date DESC
        """, nativeQuery = true)
    List<Object[]> findAllReceivablesWithDetails();

    @Modifying
    @Transactional
    @Query(value = "UPDATE receivables SET receivables_status = CAST(:newStatus as receivable_status) WHERE receivable_id = :receivableId", nativeQuery = true)
    void updateReceivableStatus(@Param("receivableId") Integer receivableId, @Param("newStatus") String newStatus);


    @Query(value = "SELECT DISTINCT apartment_id " +
            "FROM apartments " +
            "WHERE usage_status = 'Occupied' " ,
            nativeQuery = true)
    List<Integer> findActiveApartmentIds();


}
