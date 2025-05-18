package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.repository.ApartmentRepository;
import com.bluemoon.management.bluemoon.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    private ApartmentDTO convertEntityToDto(Apartment apartment) {
        if (apartment == null) {
            return null;
        }
        ApartmentDTO dto = new ApartmentDTO();
        dto.setApartmentId(apartment.getId());
        dto.setApartmentTypeId(apartment.getApartmentType().getId());
        dto.setUsableAreaSqm(apartment.getUsableAreaSqm());
        dto.setUsageStatus(apartment.getUsageStatus());
        dto.setHandoverDate(apartment.getHandoverDate());
        dto.setCurrentHeadResidentId(apartment.getCurrentHeadResident().getId());
    return dto;
    }
    public List<ApartmentDTO> getAllApartments() {
        // Gọi trực tiếp phương thức findAll() được cung cấp bởi JpaRepository
        List<Apartment> apartments = apartmentRepository.findAll();

        // Phần còn lại của logic chuyển đổi sang DTO
        return apartments.stream()
                .map(apartment -> convertEntityToDto(apartment)) // Sử dụng lambda expression
                .collect(Collectors.toList());
    }


}

