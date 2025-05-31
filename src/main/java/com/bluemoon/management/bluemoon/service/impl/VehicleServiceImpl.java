package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.VehicleCreateRequest;
import com.bluemoon.management.bluemoon.dto.VehicleDTO;
import com.bluemoon.management.bluemoon.dto.VehicleResponse;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.entity.Resident;
import com.bluemoon.management.bluemoon.entity.Vehicle;
import com.bluemoon.management.bluemoon.repository.ApartmentRepository;
import com.bluemoon.management.bluemoon.repository.ResidentsRepository;
import com.bluemoon.management.bluemoon.repository.VehicleRepository;
import com.bluemoon.management.bluemoon.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ApartmentRepository apartmentRepository;
    private final ResidentsRepository residentRepository;
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, ApartmentRepository apartmentRepository, ResidentsRepository residentRepository) {
        this.vehicleRepository = vehicleRepository;
        this.apartmentRepository = apartmentRepository;
        this.residentRepository = residentRepository;
    }

    private VehicleDTO convertEntityToDto(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(vehicle.getId());
        dto.setApartmentId(vehicle.getApartment().getId());
        dto.setApartmentNumber(vehicle.getApartment().getApartmentNumber());
        dto.setOwnerId(vehicle.getOwnerResident().getId());
        dto.setOwnerIdCardNumber(vehicle.getOwnerResident().getIdCardNumber());
        dto.setVehicleType(vehicle.getVehicleType());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setParkingCardID(vehicle.getParkingCardId());
        dto.setAssignedParkingSpot(vehicle.getAssignedParkingSpot());
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setRegistrationDate(vehicle.getRegistrationDate());
        dto.setDeregistrationDate(vehicle.getDeregistrationDate());
        dto.setVehicleStatus(vehicle.getVehiclesStatus());
        return dto;
    }

    @Override
    public List<VehicleDTO> getVehicles(){
        List<Vehicle> vehicles = vehicleRepository.findAllWithAssociations();
        return vehicles.stream().map(vehicle -> convertEntityToDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleDTO getVehicleById(Integer vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        return convertEntityToDto(vehicle);
    };


    @Override
    @Transactional
    public VehicleResponse createVehicleByQuery(VehicleCreateRequest request) {
        // Validate apartment và resident tồn tại
        validateAndGetApartmentResident(request.getApartmentNumber(), request.getCardIdNumber());
        // Thực hiện insert và lấy ID trả về
         vehicleRepository.insertVehicleByApartmentAndResident(
                    request.getApartmentNumber(),
                request.getCardIdNumber(),
                 String.valueOf(request.getVehicleType()),
                request.getLicensePlate(),
                request.getParkingCardId(),
                request.getAssignedParkingSpot(),
                request.getBrand(),
                request.getModel(),
                request.getRegistrationDate(),
                request.getDeregistrationDate(),
                String.valueOf(request.getVehiclesStatus())
        );
        Integer vehicleId = vehicleRepository.findVehicleIdByDetails(request.getApartmentNumber(),request.getCardIdNumber(),request.getLicensePlate());

        if (vehicleId != null) {
             Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new EntityNotFoundException("vehicle not create"));
            return  mapToResponse(vehicle);
        }

    throw new RuntimeException("vehicle not create");
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getApartment().getId(),
                vehicle.getOwnerResident().getId(),
                vehicle.getVehicleType(),
                vehicle.getLicensePlate(),
                vehicle.getParkingCardId(),
                vehicle.getAssignedParkingSpot(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getRegistrationDate(),
                vehicle.getDeregistrationDate(),
                vehicle.getVehiclesStatus()
        );
    }

    /**
     * Phiên bản validate trả về entity thay vì chỉ kiểm tra
     * @param apartmentNumber Số căn hộ
     * @param cardIdNumber Số CCCD/CMND
     * @return Array chứa [Apartment, Resident]
     */
    private Object[] validateAndGetApartmentResident(Integer apartmentNumber, String cardIdNumber) {
        Resident resident = residentRepository.findByIdCardNumber(cardIdNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy resident với card ID: " + cardIdNumber));

        Apartment apartment = apartmentRepository.findByApartmentNumber(apartmentNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy apartment với số: " + apartmentNumber));

        return new Object[]{apartment, resident};
    }
}
