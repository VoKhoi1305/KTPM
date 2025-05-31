package com.bluemoon.management.bluemoon.service.impl;


import com.bluemoon.management.bluemoon.dto.ReceivableDTO;
import com.bluemoon.management.bluemoon.dto.ShowReceivableDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.entity.Receivable;
import com.bluemoon.management.bluemoon.enums.ReceivableStatus;
import com.bluemoon.management.bluemoon.repository.ReceivableRepository;
import com.bluemoon.management.bluemoon.service.ReceivableService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ReceivableServiceImpl implements ReceivableService {

    private final ReceivableRepository receivableRepository;
    @Autowired
    public ReceivableServiceImpl(ReceivableRepository receivableRepository) {
        this.receivableRepository = receivableRepository;
    }

    @Override
    @Transactional
    public List<ShowReceivableDTO> getAllReceivables() {
        List<Object[]> results = receivableRepository.findAllReceivablesWithDetails();

        return results.stream()
                .map(this::convertObjectArrayToDTO)
                .collect(Collectors.toList());
    }

    private ShowReceivableDTO  convertObjectArrayToDTO(Object[] row) {
        ShowReceivableDTO dto = new ShowReceivableDTO();
        dto.setReceivableId((Integer) row[0]);
        dto.setApartmentName((Integer) row[1]);
        dto.setFeeName((String) row[2]);
        dto.setQuantity((Integer) row[3]);
        dto.setStatus(ReceivableStatus.valueOf(((String) row[4]).toUpperCase()));
        dto.setPrice((Double) row[5]);
        dto.setReceivableIssueDate(((Date) row[6]).toLocalDate());
        return dto;
    }

    private ReceivableDTO  convertToDTO(Receivable receivable) {
        ReceivableDTO dto = new ReceivableDTO();
        dto.setReceivableId(receivable.getId());
        dto.setApartmentId(receivable.getApartment().getId());
        dto.setFeeTypeId(receivable.getFeeType().getId());
        dto.setQuantity(receivable.getQuantity());
        dto.setReceivableStatus(receivable.getReceivablesStatus());
        dto.setReceivableDeadline(receivable.getPaymentDeadline());
        return dto;
    }


    @Override
    @Transactional
    public ReceivableDTO createReceivable(ReceivableDTO receivableDTO) {

        LocalDate currentDate = LocalDate.now();
        Integer newId = receivableRepository.insertReceivable(
                receivableDTO.getApartmentId(),
                receivableDTO.getFeeTypeId(),
                String.valueOf(receivableDTO.getReceivableStatus()),
                receivableDTO.getQuantity(),
                currentDate,
                currentDate.plusWeeks(1)
        );

        Receivable savedReceivable = receivableRepository.findById(newId)
                      .orElseThrow(() -> new RuntimeException("Receivable not found with id: " + newId));

        return convertToDTO(savedReceivable);

    }

    @Override
    public int createForAllReceivable(List<Integer> ids, Integer feeTypeId) {
        AtomicInteger createdCount = new AtomicInteger(0);
        ids.forEach(apartmentId -> {
            createReceivableForApartment( apartmentId, feeTypeId);
            createdCount.incrementAndGet();
        });

        return createdCount.get();
    }


    @Transactional
    public void createReceivableForApartment(Integer apartmentId, Integer feeTypeId) {
        LocalDate issueDate = LocalDate.now();
        LocalDate paymentDeadline = issueDate.plusWeeks(1);
        receivableRepository.insertReceivableForAllApartments(
          apartmentId,
          feeTypeId,
          "Issued",
          issueDate,
          paymentDeadline
        );
    }

    @Override
    @Transactional
    public ReceivableDTO updateReceivable(Integer id, String status) {
        Receivable receivable = receivableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + id));
        receivableRepository.updateReceivableStatus(id,status);
        return convertToDTO(receivable);
    }
}
