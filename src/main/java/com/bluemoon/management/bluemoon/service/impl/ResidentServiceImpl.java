package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.ResidentsDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.entity.Resident;
import com.bluemoon.management.bluemoon.enums.Gender;
import com.bluemoon.management.bluemoon.repository.ApartmentRepository;
import com.bluemoon.management.bluemoon.repository.ResidentsRepository;
import com.bluemoon.management.bluemoon.service.ResidentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ResidentServiceImpl implements ResidentService {

    private final ResidentsRepository residentsRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ResidentServiceImpl(ResidentsRepository residentsRepository, ApartmentRepository apartmentRepository) {
        this.residentsRepository = residentsRepository;
        this.apartmentRepository = apartmentRepository;
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

    @Override
    @Transactional
    public ResidentsDTO createResident(ResidentsDTO residentDTO) {
        try{
            Gender.valueOf(String.valueOf(residentDTO.getGender()));
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid gender: " + residentDTO.getGender());
        }

        Apartment apartment = apartmentRepository.findById(residentDTO.getPermanentResidenceApartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with ID: " + residentDTO.getPermanentResidenceApartmentId()));

        Integer newId = residentsRepository.insertResident(
                residentDTO.getResidentName(),
                residentDTO.getResidentPhoneNumber(),
                residentDTO.getResidentDoB(),
                String.valueOf(residentDTO.getGender()),
                residentDTO.getPermanentResidenceApartmentId(),
                residentDTO.getRelationshipToHead(),
                residentDTO.getNationality(),
                String.valueOf(residentDTO.getResidencyStatus()),
                residentDTO.getPlaceOfBirth(),
                residentDTO.getIDcardNumber(),
                residentDTO.getIDCardIssueDate()
        );

        Resident saveResident = residentsRepository.findById(newId)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve saved apartment"));
        return convertEntityToDto(saveResident);
    }
}
