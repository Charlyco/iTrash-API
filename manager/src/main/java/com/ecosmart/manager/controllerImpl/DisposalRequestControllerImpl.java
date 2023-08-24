package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.DisposalRequestController;
import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.ecosmart.manager.service.DisposalRequestService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DisposalRequestControllerImpl implements DisposalRequestController {
    private final DisposalRequestService requestService;

    public DisposalRequestControllerImpl(DisposalRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public ResponseEntity<List<DisposalRequestDto>> getRequestsByStatus(RequestStatus requestStatus) {
        var requests = requestService.getRequestsByStatus(requestStatus);
        return ResponseEntity.ok(requests);
    }

    @Override
    public ResponseEntity<List<DisposalRequestDto>> getAllRequestHandledByAgent(Integer agentId) {
        var requests = requestService.getAllRequestHandledByAgent(agentId);
        return ResponseEntity.ok(requests);
    }

    @Override
    public ResponseEntity<String> getRequestStatus(Integer requestId) {
        String status = requestService.getRequestStatus(requestId);
        return ResponseEntity.ok(status);
    }

    @Override
    public ResponseEntity<String> updateRequestStatus(Integer requestId, RequestStatus status) {
        String newStatus = requestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(newStatus);
    }

    @Override
    public ResponseEntity<String> assignRequestToAgent(Integer requestId, Integer agentId) {
        String agentUserName = requestService.assignRequestToAgent(requestId, agentId);
        return ResponseEntity.ok(agentUserName);
    }

    @Override
    public ResponseEntity<String> generateRequest(DisposalRequestDto requestDto) throws FirebaseMessagingException {
        String requestStatus = requestService.generateRequest(requestDto);
        return ResponseEntity.ok(requestStatus);
    }

    @Override
    public ResponseEntity<Integer> subscribeToTopic(List<String> tokens, String topic) throws FirebaseMessagingException {
        return ResponseEntity.ok(requestService.subScribeClientDevice(tokens, topic));
    }

    @Override
    public ResponseEntity<Integer> unsubscribeToTopic(List<String> tokens, String topic) throws FirebaseMessagingException {
        return ResponseEntity.ok(requestService.unsubscribeClientDevice(tokens, topic));
    }
}
