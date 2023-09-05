package com.ecosmart.manager.controller;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/bin")
public interface BinController {
    @PostMapping
    ResponseEntity<Integer> createNewBin(@RequestBody BinDto binDto);
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
}
