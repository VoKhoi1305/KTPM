package com.bluemoon.management.bluemoon.controller;

import com.bluemoon.management.bluemoon.dto.ReceivableDTO;
import com.bluemoon.management.bluemoon.dto.ShowReceivableDTO;
import com.bluemoon.management.bluemoon.service.ReceivableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/receivable")
public class ReceivableController {
    private final ReceivableService receivableService;

    public ReceivableController(ReceivableService receivableService) {
        this.receivableService = receivableService;
    }

    @GetMapping
    public ResponseEntity<List<ShowReceivableDTO>> getAllReceivables() {
        List<ShowReceivableDTO> receivables = receivableService.getAllReceivables();
        return ResponseEntity.ok(receivables);
    }

    @PostMapping("/add")
    public ResponseEntity<ReceivableDTO> addReceivable(@RequestBody ReceivableDTO receivableDTO) {
            ReceivableDTO receivableDTO1 = receivableService.createReceivable(receivableDTO);
         return new ResponseEntity<>(receivableDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{receivableId}")
    ResponseEntity<ReceivableDTO> updateReceivable(@RequestBody Map<String, Object> requestBody) {
            Integer id = (Integer) requestBody.get("receivableId");
            String status = requestBody.get("receivableStatus").toString();
        ReceivableDTO receivable =  receivableService.updateReceivable(id,status);
        return new ResponseEntity<>(receivable, HttpStatus.OK);
    }
}
