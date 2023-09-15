package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.BinRequest;
import com.ecosmart.manager.data.BinRequestStatus;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.dto.BinDto;
import com.ecosmart.manager.dto.BinRequestDto;
import com.ecosmart.manager.repository.BinRepository;
import com.ecosmart.manager.repository.BinRequestRepository;
import com.ecosmart.manager.service.BinService;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BinServiceImpl implements BinService {
    private  final BinRepository binRepository;
    private final EntityDtoConverter entityDtoConverter;
    private final BinRequestRepository binRequestRepository;

    public BinServiceImpl(BinRepository binRepository, EntityDtoConverter entityDtoConverter, BinRequestRepository binRequestRepository) {
        this.binRepository = binRepository;
        this.entityDtoConverter = entityDtoConverter;
        this.binRequestRepository = binRequestRepository;
    }

    public void createNewBin(BinRequest request) {
        BinDto binDto = new BinDto();
        binDto.setBinSize(request.getBinSize().name());
        binDto.setLatitude(request.getLocation().getLatitude());
        binDto.setLongitude(request.getLocation().getLongitude());
        binDto.setAddress(request.getDetailedAddress());
        binDto.setOwnership(request.getBinOwnership().name());
        binDto.setBinStatus(BinStatus.EMPTY.name());
        binDto.setUserId(request.getCustomer().getUserId());
        binRepository.save(entityDtoConverter.convertDtoToBin(binDto));
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

    @Override
    public Integer requestForBin(BinRequestDto binRequestDto) {
        BinRequest newRequest = binRequestRepository.save(entityDtoConverter.convertDtoToBinRequest(binRequestDto));
        return newRequest.getRequestId();
    }

    @Override
    public BinRequestDto getRequestById(Integer requestId) {
        return entityDtoConverter.convertBinRequestToDto(binRequestRepository.findById(requestId).orElseThrow());
    }

    @Override
    public List<BinRequestDto> getAllRequests() {
        List<BinRequestDto> requestDtoList = new ArrayList<>();
        List<BinRequest> requestList = binRequestRepository.findAll();
        requestList.forEach(binRequest -> requestDtoList.add(
                entityDtoConverter.convertBinRequestToDto(binRequest)
        ));
        return requestDtoList;
    }

    @Override
    public List<BinRequestDto> getRequestsByStatus(String requestStatus) {
        List<BinRequestDto> requestDtoList = new ArrayList<>();
        List<BinRequest> requestList = binRequestRepository.findBinRequestByRequestStatus(BinRequestStatus.valueOf(requestStatus));
        requestList.forEach(binRequest -> requestDtoList.add(
                entityDtoConverter.convertBinRequestToDto(binRequest)
        ));
        return requestDtoList;
    }

    @Override
    public Boolean updateBinRequestStatus(Integer requestId, String requestStatus) {
        if (binRequestRepository.existsById(requestId)) {
            BinRequest request = binRequestRepository.findById(requestId).orElseThrow();
            request.setRequestStatus(BinRequestStatus.valueOf(requestStatus));
            binRequestRepository.save(request);
            if (Objects.equals(requestStatus, BinRequestStatus.APPROVED.name())) {
                createNewBin(request);
            }
        }
        return false;
    }
}
