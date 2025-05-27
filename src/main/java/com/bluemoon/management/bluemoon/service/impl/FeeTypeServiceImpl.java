package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.dto.FeeTypesDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.entity.FeeType;
import com.bluemoon.management.bluemoon.enums.FeeCategory;
import com.bluemoon.management.bluemoon.repository.FeeTypeRepository;
import com.bluemoon.management.bluemoon.service.FeeTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {
    private final FeeTypeRepository feeTypeRepository;

    public FeeTypeServiceImpl(FeeTypeRepository feeTypeRepository) {
        this.feeTypeRepository = feeTypeRepository;
    }

    private FeeTypesDTO convertEntityToDto( FeeType feeType ) {
        if ( feeType == null) {
            return null;
        }
        FeeTypesDTO dto = new FeeTypesDTO();
        dto.setFeeName( feeType.getFeeName() );
        dto.setCategory(feeType.getCategory());
        dto.setFeeFrequency(feeType.getFrequency());
        dto.setCalculationMethod( feeType.getCalculationMethod() );
        dto.setFeeStatus(feeType.getFeeStatus());
        dto.setUnitPrice(feeType.getUnitPrice());
        dto.setMandatory(feeType.getIsMandatory());
        return dto;
    }

    @Override
    @Transactional
    public List<FeeTypesDTO> getAllFeeTypes(){
        List<FeeType> feeTypesDTOS = feeTypeRepository.findAll();
        return feeTypesDTOS.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
