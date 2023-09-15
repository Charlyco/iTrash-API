package com.ecosmart.manager.controller;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinRequest;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import com.ecosmart.manager.dto.BinRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/bin")
public interface BinController {
    @PutMapping("/{binId}/status")
    ResponseEntity<Void> updateBinStatus(@PathVariable("binId") Integer binId, @RequestParam BinStatus binStatus);
    @PutMapping("/{binId}")
    ResponseEntity<Void> updateBinDetails(@PathVariable Integer binId, @RequestBody BinDto update);
    @GetMapping("/{binId}")
    ResponseEntity<BinDto> getBinById(@PathVariable Integer binId);
    @GetMapping("/userId")
    ResponseEntity<List<BinDto>> getBinsByUserId(@RequestParam Integer userId);
    @GetMapping
    ResponseEntity<List<BinDto>> getAllBins();
    @DeleteMapping("/binId")
    ResponseEntity<Void> deleteBinById(@RequestParam Integer binId);
    @PostMapping("/request")
    ResponseEntity<Integer> requestForBin(@RequestBody BinRequestDto binRequest);
    @GetMapping("/request/{requestId}")
    ResponseEntity<BinRequestDto> getBinRequestById(@PathVariable("requestId") Integer requestId);
    @GetMapping("/request")
    ResponseEntity<List<BinRequestDto>> getRequestsByStatus(@RequestParam("requestStatus") String requestStatus);
    @GetMapping("/request/all")
    ResponseEntity<List<BinRequestDto>> getAllBinRequests();
    @PutMapping("/request/{requestId}")
    ResponseEntity<Boolean> updateBinRequestStatus(@PathVariable("requestId") Integer requestId, @RequestParam("requestStatus") String requestStatus);
}
