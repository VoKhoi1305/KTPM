package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.ResidentsDTO;
import com.bluemoon.management.bluemoon.entity.Resident;
import com.bluemoon.management.bluemoon.repository.ResidentsRepository;
import com.bluemoon.management.bluemoon.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ResidentServiceImpl implements ResidentService {

    private final ResidentsRepository residentsRepository;

    @Autowired
    public ResidentServiceImpl(ResidentsRepository residentsRepository) {
        this.residentsRepository = residentsRepository;
    }

    private ResidentsDTO convertEntityToDto(Resident resident) {
        if (resident == null) {
            return null;
        }
        ResidentsDTO residentDTO = new ResidentsDTO();
        residentDTO.setResidentId(resident.getId());
        residentDTO.setResidentName(resident.getResidentFullname());
        residentDTO.setResidentPhoneNumber(resident.getResidentPhoneNumber());
        residentDTO.setResidentDoB(resident.getResidentDob());
        residentDTO.setGender(resident.getResidentGender());
        residentDTO.setPermanentResidenceApartmentId(resident.getPermanentResidenceApartment().getId());
        residentDTO.setRelationshipToHead(resident.getRelationshipToHead());
        residentDTO.setPlaceOfBirth(resident.getPlaceOfBirth());
        residentDTO.setNationality(resident.getNationality());
        residentDTO.setResidencyStatus(resident.getResidencyStatus());
        residentDTO.setIDcardNumber(resident.getIdCardNumber());
        residentDTO.setIDCardIssueDate(resident.getIdCardIssueDate());
        return residentDTO;
    }

    @Override
    @Transactional(readOnly = true)
   public List<ResidentsDTO> getAllResidents() {
        List<Resident> residents = residentsRepository.findAll();
        return residents.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

}
