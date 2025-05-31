package com.bluemoon.management.bluemoon.service;


import com.bluemoon.management.bluemoon.dto.FeeTypesDTO;
import com.bluemoon.management.bluemoon.entity.FeeType;

import java.util.List;

public interface FeeTypeService {
    List<FeeTypesDTO> getAllFeeTypes();
    FeeTypesDTO insertFeeType(FeeTypesDTO feeTypesDTO);
}
