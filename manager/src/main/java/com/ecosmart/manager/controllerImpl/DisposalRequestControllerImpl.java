package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.DisposalRequestController;
import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.ecosmart.manager.service.DisposalRequestService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
    public ResponseEntity<List<DisposalRequestDto>> getPendingRequestByLocation(Double latitude, Double longitude) {
        return ResponseEntity.ok(requestService.getRequestListByLocation(latitude, longitude));
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
    public ResponseEntity<Boolean> assignRequestToAgent(Integer requestId, Integer agentId) {
        var isAssigned = requestService.assignRequestToAgent(requestId, agentId);
        return ResponseEntity.ok(isAssigned);
    }

    @Override
    public ResponseEntity<Integer> generateRequest(DisposalRequestDto requestDto) throws FirebaseMessagingException {
        Integer requestId = requestService.generateRequest(requestDto);
        return ResponseEntity.ok(requestId);
    }

    @Override
    public ResponseEntity<DisposalRequestDto> getRequestById(Integer requestId) {
        return ResponseEntity.ok(requestService.getRequestById(requestId));
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
