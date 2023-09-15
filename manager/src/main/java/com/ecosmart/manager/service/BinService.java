package com.ecosmart.manager.service;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinRequest;
import com.ecosmart.manager.data.BinRequestStatus;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import com.ecosmart.manager.dto.BinRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BinService {
    Boolean updateBinStatus(Integer binId, BinStatus binStatus);
    Boolean updateBinDetails(Integer binId, BinDto update);
    BinDto getBinById(Integer binId);
    List<BinDto> getBinsByUserId(Integer userId);
    List<BinDto> getAllBins();
    Boolean deleteBinById(Integer binId);
    Integer requestForBin(BinRequestDto binRequestDto);
    BinRequestDto getRequestById(Integer requestId);
    List<BinRequestDto> getAllRequests();
    List<BinRequestDto> getRequestsByStatus(String requestStatus);
    Boolean updateBinRequestStatus(Integer requestId, String requestStatus);
}
