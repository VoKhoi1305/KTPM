package com.bluemoon.management.bluemoon.service.impl;


import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.dto.ApartmentTypeDTO;
import com.bluemoon.management.bluemoon.entity.ApartmentType;
import com.bluemoon.management.bluemoon.repository.ApartmentTypeRepository;
import com.bluemoon.management.bluemoon.service.ApartmentTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ApartmentTypeServiceImpl implements ApartmentTypeService {

    private final ApartmentTypeRepository apartmentTypeRepository;

    @Autowired
    public ApartmentTypeServiceImpl(ApartmentTypeRepository apartmentTypeRepository) {
        this.apartmentTypeRepository = apartmentTypeRepository;
    }

    @Override
    public ApartmentType findById(Integer id) {
        return apartmentTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy loại căn hộ với ID: " + id));
    }


}
