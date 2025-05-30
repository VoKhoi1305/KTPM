package com.bluemoon.management.bluemoon.service;

import com.bluemoon.management.bluemoon.dto.ReceivableDTO;
import com.bluemoon.management.bluemoon.dto.ResidentsDTO;
import com.bluemoon.management.bluemoon.dto.ShowReceivableDTO;

import java.util.List;

public interface ReceivableService {
    List<ShowReceivableDTO> getAllReceivables();
    ReceivableDTO createReceivable(ReceivableDTO receivable);
    ReceivableDTO updateReceivable(Integer id, String status);
    int createForAllReceivable(List<Integer> ids, Integer feeTypeId);
    List<ShowReceivableDTO> getAllReceivablesWithFeeType(Integer feeTypeId);
}
