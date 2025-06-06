package com.bluemoon.management.bluemoon.service;

import com.bluemoon.management.bluemoon.dto.ApartmentCreateDTO;
import com.bluemoon.management.bluemoon.dto.ApartmentDTO;
import com.bluemoon.management.bluemoon.entity.Apartment;
import com.bluemoon.management.bluemoon.enums.ApartmentUsageStatus;


import java.util.ArrayList;
import java.util.List;

public interface ApartmentService {
    /**
     *
     * @return
     */
    List<ApartmentDTO> getAllApartments();
    /**
     * Tạo một apartment mới
     * @param apartmentCreateDTO thông tin apartment cần tạo
     * @return thông tin apartment đã tạo
     * @author KhoiVA
     */
    ApartmentDTO createApartment(ApartmentCreateDTO apartmentCreateDTO);

    /**
     * Thay đổi trạng thái sử dụng của apartment
     * @param apartmentId ID của apartment
     * @param usageStatus trạng thái sử dụng mới
     * @return thông tin apartment sau khi cập nhật
     * @author KhoiVA
     */
    ApartmentDTO updateApartmentUsageStatus(Integer apartmentId, ApartmentUsageStatus usageStatus);


    /**
     * thay đổi id của chủ hộ
     * @param apartmentId
     * @param headResidentId
     * @return thông tin apartment sau khi cập nhat
     * @author KhoiVA
     */
    ApartmentDTO updateHeadResidentId(Integer apartmentId, Integer headResidentId);

    ApartmentDTO getApartmentById(Integer apartmentId);
}
