package com.bluemoon.management.bluemoon.service;

import com.bluemoon.management.bluemoon.dto.ResidentsDTO;
import com.bluemoon.management.bluemoon.entity.Resident;
import java.util.List;

public interface ResidentService {
    List<ResidentsDTO> getAllResidents();
    ResidentsDTO createResident(ResidentsDTO residentsDTO);
}
