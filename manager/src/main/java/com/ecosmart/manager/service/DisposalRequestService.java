package com.ecosmart.manager.service;

import com.ecosmart.manager.data.Location;
import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DisposalRequestService {
    List<DisposalRequestDto> getRequestsByStatus(RequestStatus requestStatus);
    List<DisposalRequestDto> getRequestListByLocation(Double latitude, Double longitude);
    List<DisposalRequestDto> getAllRequestHandledByAgent(Integer agentId);
    String getRequestStatus(Integer requestId);
    Boolean updateRequestStatus(Integer requestId, String requestStatus);
    Boolean assignRequestToAgent(Integer requestId, Integer agentId);
    Integer generateRequest(DisposalRequestDto requestDto) throws FirebaseMessagingException;
    void sendMessage(String topic, DisposalRequestDto requestDto) throws FirebaseMessagingException;
    Integer subScribeClientDevice(List<String> tokens, String topic) throws FirebaseMessagingException;
    Integer unsubscribeClientDevice(List<String> tokens, String topic) throws FirebaseMessagingException;
    DisposalRequestDto getRequestById(Integer requestId);
}
