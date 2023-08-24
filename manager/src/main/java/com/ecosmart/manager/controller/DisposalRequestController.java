package com.ecosmart.manager.controller;

import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disposal")
public interface DisposalRequestController {
    @GetMapping("/status")
    ResponseEntity<List<DisposalRequestDto>> getRequestsByStatus(@RequestParam("status") RequestStatus requestStatus);

    @GetMapping("/{agentId}")
    ResponseEntity<List<DisposalRequestDto>> getAllRequestHandledByAgent(@PathVariable Integer agentId );

    @GetMapping("/request/{requestId}")
    ResponseEntity<String> getRequestStatus (@PathVariable Integer requestId);

    @PutMapping("/update/{requestId}")
    ResponseEntity<String> updateRequestStatus(@PathVariable Integer requestId, @RequestParam("status") RequestStatus status);

    @PutMapping("/update/assign/{requestId}")
    ResponseEntity<String> assignRequestToAgent(@PathVariable Integer requestId, @RequestParam("agentId") Integer agentId);

    @PostMapping("/create")
    ResponseEntity<String> generateRequest(@RequestBody DisposalRequestDto requestDto) throws FirebaseMessagingException;

    @PutMapping("/notification/subscribe")
    ResponseEntity<Integer> subscribeToTopic(@RequestParam List<String> tokens, @RequestParam String topic) throws FirebaseMessagingException;

    @PutMapping("/notification/unsubscribe")
    ResponseEntity<Integer> unsubscribeToTopic(@RequestParam List<String> tokens, @RequestParam String topic) throws FirebaseMessagingException;

}
