package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.BinController;
import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
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
    public ResponseEntity<Integer> createNewBin(BinDto binDto) {
        return ResponseEntity.ok(binService.createNewBin(binDto));
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
}
