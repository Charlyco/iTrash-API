package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.DisposalRequest;
import com.ecosmart.manager.data.Location;
import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.ecosmart.manager.repository.AgentRepository;
import com.ecosmart.manager.repository.DisposalRequestRepository;
import com.ecosmart.manager.service.DisposalRequestService;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DisposalRequestServiceImpl implements DisposalRequestService {
    private final DisposalRequestRepository requestRepository;
    private final AgentRepository agentRepository;
    private final EntityDtoConverter entityDtoConverter;

    public DisposalRequestServiceImpl(DisposalRequestRepository requestRepository, AgentRepository agentRepository, EntityDtoConverter entityDtoConverter) {
        this.requestRepository = requestRepository;
        this.agentRepository = agentRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public List<DisposalRequestDto> getAvailableRequests(RequestStatus requestStatus) {
        List<DisposalRequestDto> availableRequests = new ArrayList<>();
        requestRepository.findAllByRequestStatus(requestStatus).forEach(request ->
                availableRequests.add(entityDtoConverter.convertRequestToDto(request))
                );
        return availableRequests;
    }

    @Override
    public List<DisposalRequestDto> getAllRequestHandledByAgent(Integer agentId) {
        List<DisposalRequestDto> availableRequests = new ArrayList<>();
        requestRepository.findAllByAgent_UserId(agentId).forEach(request ->
                availableRequests.add(entityDtoConverter.convertRequestToDto(request))
        );
        return availableRequests;
    }

    @Override
    public String getRequestStatus(Integer requestId) {
        DisposalRequest request = requestRepository.findById(requestId).orElseThrow();
        return request.getRequestStatus().name();
    }

    @Override
    public String updateRequestStatus(Integer requestId, RequestStatus requestStatus) {
        if (requestRepository.findById(requestId).isPresent()) {
            DisposalRequest request = requestRepository.findById(requestId).orElseThrow();
            request.setRequestStatus(requestStatus);
            requestRepository.save(request);
            return requestRepository.findById(requestId).orElseThrow().getRequestStatus().name();
        }
        return "Disposal Request not found";
    }

    @Override
    public String assignRequestToAgent(Integer requestId, Integer agentId) {
        if (requestRepository.findById(requestId).isPresent()) {
            DisposalRequest request = requestRepository.findById(requestId).orElseThrow();
            request.setAgent(agentRepository.findById(agentId).orElseThrow());
            requestRepository.save(request);
            return requestRepository.findById(requestId).orElseThrow().getAgent().getUsername();
        }
        return "Disposal Request not found";
    }

    @Override
    public String generateRequest(Integer binId, Location location) {
        return null;
    }
}
