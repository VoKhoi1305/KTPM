package com.bluemoon.management.bluemoon.service;

import com.bluemoon.management.bluemoon.dto.VehicleCreateRequest;
import com.bluemoon.management.bluemoon.dto.VehicleDTO;
import com.bluemoon.management.bluemoon.dto.VehicleResponse;
import com.bluemoon.management.bluemoon.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getVehicles();
    VehicleResponse createVehicleByQuery(VehicleCreateRequest request);

    VehicleDTO getVehicleById(Integer vehicleId);
}
