package com.ecosmart.manager.service;

import com.ecosmart.manager.data.Location;
import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DisposalRequestService {
    List<DisposalRequestDto> getAvailableRequests(RequestStatus requestStatus);
    List<DisposalRequestDto> getAllRequestHandledByAgent(Integer agentId);
    String getRequestStatus(Integer requestId);
    String updateRequestStatus(Integer requestId, RequestStatus requestStatus);
    String assignRequestToAgent(Integer requestId, Integer agentId);
    String generateRequest(Integer binId, Location location);
}
