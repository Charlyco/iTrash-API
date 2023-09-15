package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.BinController;
import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinRequest;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import com.ecosmart.manager.dto.BinRequestDto;
import com.ecosmart.manager.service.BinService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class BinControllerImpl implements BinController {
    private final BinService binService;

    public BinControllerImpl(BinService binService) {
        this.binService = binService;
    }

    @Override
    public ResponseEntity<Void> updateBinStatus(Integer binId, BinStatus binStatus) {
        if (binService.updateBinStatus(binId, binStatus)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> updateBinDetails(Integer binId, BinDto update) {
        if (binService.updateBinDetails(binId, update)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<BinDto> getBinById(Integer binId) {
        return ResponseEntity.ok(binService.getBinById(binId));
    }

    @Override
    public ResponseEntity<List<BinDto>> getBinsByUserId(Integer userId) {
        return ResponseEntity.ok(binService.getBinsByUserId(userId));
    }

    @Override
    public ResponseEntity<List<BinDto>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @Override
    public ResponseEntity<Void> deleteBinById(Integer binId) {
        if (binService.deleteBinById(binId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Integer> requestForBin(BinRequestDto binRequestDto) {
        return ResponseEntity.ok(binService.requestForBin(binRequestDto));
    }

    @Override
    public ResponseEntity<BinRequestDto> getBinRequestById(Integer requestId) {
        return ResponseEntity.ok(binService.getRequestById(requestId));
    }

    @Override
    public ResponseEntity<List<BinRequestDto>> getRequestsByStatus(String requestStatus) {
        return ResponseEntity.ok(binService.getRequestsByStatus(requestStatus));
    }

    @Override
    public ResponseEntity<List<BinRequestDto>> getAllBinRequests() {
        return ResponseEntity.ok(binService.getAllRequests());
    }

    @Override
    public ResponseEntity<Boolean> updateBinRequestStatus(Integer requestId, String requestStatus) {
        return ResponseEntity.ok(binService.updateBinRequestStatus(requestId, requestStatus));
    }
}
