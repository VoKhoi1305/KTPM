package com.bluemoon.management.bluemoon.service;

import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;


import java.util.ArrayList;
import java.util.List;

public interface ApartmentService {
    List<ApartmentDTO> getAllApartments();
}
