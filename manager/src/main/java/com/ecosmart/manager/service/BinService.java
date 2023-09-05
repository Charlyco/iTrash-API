package com.ecosmart.manager.service;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BinService {
    Integer createNewBin(BinDto binDto);
    Boolean updateBinStatus(Integer binId, BinStatus binStatus);
    Boolean updateBinDetails(Integer binId, BinDto update);
    BinDto getBinById(Integer binId);
    List<BinDto> getBinsByUserId(Integer userId);
    List<BinDto> getAllBins();
    Boolean deleteBinById(Integer binId);
}
