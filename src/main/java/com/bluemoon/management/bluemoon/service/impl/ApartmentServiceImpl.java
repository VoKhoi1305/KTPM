package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.ApartmentCreateDTO;
import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.entity.Resident;
import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;
import com.bluemoon.management.bluemoon.repository.ApartmentRepository;
import com.bluemoon.management.bluemoon.repository.ResidentsRepository;
import com.bluemoon.management.bluemoon.service.ApartmentService;
import com.bluemoon.management.bluemoon.service.ApartmentTypeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ApartmentServiceImpl implements ApartmentService {


    private final ApartmentRepository apartmentRepository;
    private final ApartmentTypeService apartmentTypeService; // Thêm dòng này
    private final ResidentsRepository residentsRepository;
    //private final ResidentsRepository residentsRepository;
    @Autowired
    public ApartmentServiceImpl(
            ApartmentRepository apartmentRepository,
            ApartmentTypeService apartmentTypeService,
            ResidentsRepository residentsRepository) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentTypeService = apartmentTypeService;
        this.residentsRepository = residentsRepository;
    }


    private ApartmentDTO convertEntityToDto(Apartment apartment) {
        if (apartment == null) {
            return null;
        }
        ApartmentDTO dto = new ApartmentDTO();
        dto.setApartmentId(apartment.getId());
        dto.setApartmentTypeId(apartment.getApartmentType().getId());
        dto.setUsableAreaSqm(apartment.getUsableAreaSqm());
        dto.setUsageStatus(apartment.getUsageStatus());
        dto.setApartmentNumber(apartment.getApartmentNumber());
        dto.setHandoverDate(apartment.getHandoverDate());
        dto.setCurrentHeadResidentId(apartment.getCurrentHeadResident() != null ? apartment.getCurrentHeadResident().getId() : null);
        return dto;
    }

    public List<ApartmentDTO> getAllApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        return apartments.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApartmentDTO createApartment(ApartmentCreateDTO apartmentCreateDTO) {
        // Validate the enum first
        try {
            ApartmentUsageStatus.valueOf(String.valueOf(apartmentCreateDTO.getUsageStatus()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid usage status: " + apartmentCreateDTO.getUsageStatus());
        }

        // Use the custom repository method with casting
        Integer newId = apartmentRepository.insertApartmentWithCast(
                apartmentCreateDTO.getApartmentTypeId(),
                apartmentCreateDTO.getApartmentNumber(),
                apartmentCreateDTO.getUsableAreaSqm(),
                String.valueOf(apartmentCreateDTO.getUsageStatus()),
                apartmentCreateDTO.getHandoverDate());

        // Fetch the newly created apartment
        Apartment savedApartment = apartmentRepository.findById(newId)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve saved apartment"));

        return convertEntityToDto(savedApartment);
    }


    @Override
    @Transactional
    public ApartmentDTO updateApartmentUsageStatus(Integer apartmentId, ApartmentUsageStatus usageStatus) {
        // Tìm apartment trong cơ sở dữ liệu
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + apartmentId));

        // Cập nhật trạng thái sử dụng
        //apartment.setUsageStatus(usageStatus);
        // Lưu apartment đã cập nhật
         apartmentRepository.updateUsageStatus(apartmentId, String.valueOf(usageStatus));

        // Chuyển đổi apartment đã cập nhật thành DTO và trả về
        return convertEntityToDto(apartment);
    }

    @Override
    @Transactional
    public ApartmentDTO updateHeadResidentId(Integer apartmentId, Integer headResidentId) {
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + apartmentId));

        Resident updateHeadResident = residentsRepository.findById(headResidentId)
                .orElseThrow(() -> new EntityNotFoundException("Resident not found with ID: " + headResidentId));
        apartment.setCurrentHeadResident(updateHeadResident);

        apartmentRepository.updateHeadResident(apartmentId,headResidentId);

        return convertEntityToDto(apartment);
    }
}

