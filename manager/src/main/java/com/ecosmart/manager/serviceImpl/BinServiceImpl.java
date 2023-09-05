package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import com.ecosmart.manager.repository.BinRepository;
import com.ecosmart.manager.service.BinService;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BinServiceImpl implements BinService {
    private  final BinRepository binRepository;
    private final EntityDtoConverter entityDtoConverter;

    public BinServiceImpl(BinRepository binRepository, EntityDtoConverter entityDtoConverter) {
        this.binRepository = binRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public Integer createNewBin(BinDto binDto) {
        Bin createdBin = binRepository.save(entityDtoConverter.convertDtoToBin(binDto));
        return createdBin.getBinId();
    }

    @Override
    public Boolean updateBinStatus(Integer binId, BinStatus binStatus) {
        if (binRepository.existsById(binId)) {
            var bin = binRepository.findById(binId).orElseThrow();
            bin.setBinStatus(binStatus);
            binRepository.save(bin);
            return true;
        }else return false;
    }

    @Override
    public Boolean updateBinDetails(Integer binId, BinDto update) {
        if (binRepository.existsById(binId)) {
            Bin binToUpdate = entityDtoConverter.convertDtoToBin(update);
            binToUpdate.setBinId(binId);
            binRepository.save(binToUpdate);
            return true;
        }else return false;
    }

    @Override
    public BinDto getBinById(Integer binId) {
        return entityDtoConverter.convertBinToDto(binRepository.findById(binId).orElseThrow());
    }

    @Override
    public List<BinDto> getBinsByUserId(Integer userId) {
        List<Bin> binList = binRepository.findBinsByCustomer_UserId(userId);
        List<BinDto> binDtoList = new ArrayList<>();
        if (binList != null) {
            binList.forEach(bin -> binDtoList.add(entityDtoConverter.convertBinToDto(bin)));
        }
        return binDtoList;
    }

    @Override
    public List<BinDto> getAllBins() {
        List<Bin> binList = binRepository.findAll();
        List<BinDto> binDtoList = new ArrayList<>();
        if (binList != null) {
            binList.forEach(bin -> binDtoList.add(entityDtoConverter.convertBinToDto(bin)));
        }
        return binDtoList;
    }

    @Override
    public Boolean deleteBinById(Integer binId) {
        if (binRepository.existsById(binId)) {
            binRepository.deleteById(binId);
            return true;
        }else return false;
    }
}
