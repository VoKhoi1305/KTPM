package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.FeeTypesDTO;
import com.bluemoon.management.bluemoon.entity.FeeType;
import com.bluemoon.management.bluemoon.repository.FeeTypeRepository;
import com.bluemoon.management.bluemoon.repository.ReceivableRepository;
import com.bluemoon.management.bluemoon.service.FeeTypeService;
import com.bluemoon.management.bluemoon.service.ReceivableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {
    private final FeeTypeRepository feeTypeRepository;
    private final ReceivableRepository receivableRepository;
    private final ReceivableService receivableService;
    public FeeTypeServiceImpl(FeeTypeRepository feeTypeRepository, ReceivableRepository receivableRepository, ReceivableService receivableService) {
        this.feeTypeRepository = feeTypeRepository;
        this.receivableRepository = receivableRepository;
        this.receivableService = receivableService;
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
        dto.setIsMandatory(feeType.getIsMandatory());
        dto.setIsApplyToAll(feeType.getIsApplyToAll());
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

    @Override
    @Transactional
    public FeeTypesDTO insertFeeType(FeeTypesDTO feeTypesDTO) {
        System.out.println(feeTypesDTO.getIsApplyToAll());

        Integer feeTypeId = feeTypeRepository.insertFee(
                feeTypesDTO.getFeeName(),
                String.valueOf(feeTypesDTO.getCategory()),
                String.valueOf(feeTypesDTO.getFeeFrequency()),
                String.valueOf(feeTypesDTO.getCalculationMethod()),
                String.valueOf(feeTypesDTO.getFeeStatus()),
                feeTypesDTO.getUnitPrice(),
                feeTypesDTO.getIsMandatory(),
                feeTypesDTO.getIsApplyToAll()
        );
        FeeType feeType = feeTypeRepository.findById(feeTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy loại phí"));
        System.out.println("2");
        Boolean isApplyToAll = feeTypesDTO.getIsApplyToAll();
        if(Boolean.TRUE.equals(isApplyToAll))
        {
            applyFeeTypeToAllApartments(feeTypeId);
        }
        return convertEntityToDto(feeType);
    }

    @Transactional
    public void applyFeeTypeToAllApartments(Integer feeTypeId) {

        List<Integer> activeApartmentIds = receivableRepository.findActiveApartmentIds();

        int createCount = receivableService.createForAllReceivable(activeApartmentIds, feeTypeId);
    }
}
