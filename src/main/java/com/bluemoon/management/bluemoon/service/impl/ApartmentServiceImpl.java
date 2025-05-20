package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.ApartmentCreateDTO;
import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.entity.ApartmentType;
import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;
import com.bluemoon.management.bluemoon.repository.ApartmentRepository;
import com.bluemoon.management.bluemoon.service.ApartmentService;
import com.bluemoon.management.bluemoon.service.ApartmentTypeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {


    private final ApartmentRepository apartmentRepository;
    private final ApartmentTypeService apartmentTypeService; // Thêm dòng này

    @Autowired
    public ApartmentServiceImpl(
            ApartmentRepository apartmentRepository,
            ApartmentTypeService apartmentTypeService) { // Thêm tham số này
        this.apartmentRepository = apartmentRepository;
        this.apartmentTypeService = apartmentTypeService; // Thêm dòng này
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
        dto.setHandoverDate(apartment.getHandoverDate());
        dto.setCurrentHeadResidentId(apartment.getCurrentHeadResident() != null ? apartment.getCurrentHeadResident().getId() : null);
    return dto;
    }
    public List<ApartmentDTO> getAllApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        // Phần còn lại của logic chuyển đổi sang DTO
        return apartments.stream()
                .map(apartment -> convertEntityToDto(apartment)) // Sử dụng lambda expression
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ApartmentDTO createApartment(ApartmentCreateDTO apartmentCreateDTO) {
        // Tạo một đối tượng Apartment mới từ DTO
        Apartment apartment = new Apartment();
        ApartmentType apartmentType = apartmentTypeService.findById(apartmentCreateDTO.getApartmentTypeId());
        apartment.setApartmentType(apartmentType);
        apartment.setUsableAreaSqm(apartmentCreateDTO.getUsableAreaSqm());
        apartment.setUsageStatus(apartmentCreateDTO.getUsageStatus());
        apartment.setHandoverDate(apartmentCreateDTO.getHandoverDate());
        apartment.setCurrentHeadResident(null);
        // Lưu apartment vào cơ sở dữ liệu
        Apartment savedApartment = apartmentRepository.save(apartment);

        return convertEntityToDto(savedApartment);
    }

    @Override
    @Transactional
    public ApartmentDTO updateApartmentUsageStatus(Integer apartmentId, ApartmentUsageStatus usageStatus) {
        // Tìm apartment trong cơ sở dữ liệu
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + apartmentId));

        // Cập nhật trạng thái sử dụng
        apartment.setUsageStatus(usageStatus);

        // Lưu apartment đã cập nhật
        Apartment updatedApartment = apartmentRepository.save(apartment);

        // Chuyển đổi apartment đã cập nhật thành DTO và trả về
        return convertEntityToDto(updatedApartment);
    }


}

