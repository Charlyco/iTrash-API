package com.ecosmart.manager.service;

import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DisposalRequestService {
    List<DisposalRequestDto> getRequestsByStatus(RequestStatus requestStatus);
    List<DisposalRequestDto> getAllRequestHandledByAgent(Integer agentId);
    String getRequestStatus(Integer requestId);
    String updateRequestStatus(Integer requestId, RequestStatus requestStatus);
    String assignRequestToAgent(Integer requestId, Integer agentId);
    String generateRequest(DisposalRequestDto requestDto) throws FirebaseMessagingException;

    void sendMessage(String topic, DisposalRequestDto requestDto) throws FirebaseMessagingException;
    Integer subScribeClientDevice(List<String> tokens, String topic) throws FirebaseMessagingException;
    Integer unsubscribeClientDevice(List<String> tokens, String topic) throws FirebaseMessagingException;
}
